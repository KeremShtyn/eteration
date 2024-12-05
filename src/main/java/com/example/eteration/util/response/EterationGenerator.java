package com.example.eteration.util.response;

import com.example.eteration.util.EterationUtil;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;


public class EterationGenerator {

    private EterationGenerator() {
    }

    public static <T> EterationResponse<T> generateSignResponse(T payload, Object... parametersWithOrderVersionReferenceIdExtras) {

        EterationResponse<T> tEterationResponse;

        if (payload instanceof Collection) {
            tEterationResponse = new EterationResponse.SignResponseBuilder<T>()
                    .withPayload(payload)
                    .build();
        } else if (payload instanceof Map) {
            Map<String, Object> resultMap = (Map) payload;
            tEterationResponse = new EterationResponse.SignResponseBuilder<T>()
                    .withPayload((T) resultMap)
                    .build();

        } else {
            tEterationResponse = new EterationResponse.SignResponseBuilder<T>()
                    .withPayload(payload)
                    .build();
        }


        if (parametersWithOrderVersionReferenceIdExtras.length > 0) {
            for (Object object : parametersWithOrderVersionReferenceIdExtras) {
             if (object instanceof String && object == parametersWithOrderVersionReferenceIdExtras[0]) {
                    tEterationResponse.setVersion((String) object);
                } else if (object instanceof String && object == parametersWithOrderVersionReferenceIdExtras[1]) {
                    tEterationResponse.setReferenceId((String) object);
                }
            }
        } else {
            tEterationResponse.setVersion(ResponseContants.SIGN_RESPONSE_VERSION);
            tEterationResponse.setReferenceId(ResponseContants.SIGN_RESPONSE_REFERENCE + EterationUtil.formatLocalDateTimeAsString(LocalDateTime.now(), ResponseContants.SIGN_KEY_DATE_TIME_FORMAT));
        }

        return tEterationResponse;
    }


    private static Sort.Direction generateDirection(String sortDirection) {
        if (sortDirection.equalsIgnoreCase("DESC")) {
            return Sort.Direction.DESC;
        } else {
            return Sort.Direction.ASC;
        }
    }

}
