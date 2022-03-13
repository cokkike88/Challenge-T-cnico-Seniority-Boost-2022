package rooftop.challenge.omunoz.services;


import lombok.extern.slf4j.Slf4j;
import net.iharder.Base64;
import org.springframework.stereotype.Service;
import rooftop.challenge.omunoz.models.payloads.PostPayload;
import rooftop.challenge.omunoz.models.responses.GetResultResponse;

import java.io.IOException;

@Service
@Slf4j
public class EncodeDecodeService {


    public String encodeGetResultObject (GetResultResponse result) throws IOException {
        var hash = Base64.encodeObject(result);
        return hash;
    }

    public GetResultResponse decodeGetResultObject(String hash) throws IOException, ClassNotFoundException {
        var result = Base64.decodeToObject(hash, 0, GetResultResponse.class.getClassLoader());
        return (GetResultResponse)result;
    }

    public String encodePostPayload(PostPayload payload) throws IOException {
        var hash = Base64.encodeObject(payload);
        return hash;
    }

    public PostPayload decodePostPayload(String hash) throws IOException, ClassNotFoundException {
        var result = Base64.decodeToObject(hash, 0, PostPayload.class.getClassLoader());
        return (PostPayload)result;
    }

}
