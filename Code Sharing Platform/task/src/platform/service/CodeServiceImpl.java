package platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import platform.model.InputCode;
import platform.model.ResponseCode;
import platform.model.SharingCode;
import platform.repository.CodeRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class CodeServiceImpl implements CodeService {

    private final CodeRepository codeRepository;

    @Autowired
    public CodeServiceImpl(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    public SharingCode save(InputCode inputCode) {
        SharingCode newCode = new SharingCode(inputCode.getCode());
        newCode.setTime(inputCode.getTime());
        newCode.setViews(inputCode.getViews());
        return codeRepository.save(newCode);
    }

    public ResponseCode findSharingCodeById(String id) {
        SharingCode code = codeRepository.findSharingCodesById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
        ResponseCode responseCode = new ResponseCode(code);
        responseCode.setTimeRestriction(checkTimeRestriction(code));
        responseCode.setViewsRestriction(checkViewsRestriction(code));
        if (responseCode.isTimeRestriction()) {
            int leftLifeTime = findLeftLifeTime(code.getLocalDate(), LocalDateTime.now(), code.getTime());
            if (leftLifeTime <= 0) {
                codeRepository.delete(code);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
            }
            responseCode.setTime(leftLifeTime);
        }
        if (responseCode.isViewsRestriction()) {
            code.setViews(code.getViews() - 1);
            codeRepository.save(code);
            if (code.getViews() == 0) {
                codeRepository.delete(code);
            }
            responseCode.setViews(code.getViews());
        }
        return responseCode;
    }

    public List<SharingCode> getLatest() {
        return codeRepository.findTop10ByTimeEqualsAndViewsEqualsOrderByDateDesc(0, 0);
    }

    public boolean checkViewsRestriction(SharingCode code) {
        return code.getViews() > 0;
    }

    public boolean checkTimeRestriction(SharingCode code) {
        return code.getTime() > 0;
    }

    public int findLeftLifeTime(LocalDateTime startTime, LocalDateTime currentTime, int lifetime) {
        int elapsedTime = (int) ChronoUnit.SECONDS.between(startTime, currentTime);
        return lifetime - elapsedTime;
    }

}
