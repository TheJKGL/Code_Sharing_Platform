package platform.service;

import org.springframework.http.ResponseEntity;
import platform.model.InputCode;
import platform.model.ResponseCode;
import platform.model.SharingCode;

import java.util.List;
import java.util.UUID;

public interface CodeService {
    SharingCode save(InputCode toSave);

    ResponseCode findSharingCodeById(String id);

    List<SharingCode> getLatest();

    boolean checkTimeRestriction(SharingCode code);

    boolean checkViewsRestriction(SharingCode code);
}
