package rooftop.challenge.omunoz.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import rooftop.challenge.omunoz.models.entities.HashData;
import rooftop.challenge.omunoz.models.mapper.IGlobalMapper;
import rooftop.challenge.omunoz.models.payloads.PostPayload;
import rooftop.challenge.omunoz.models.responses.GetResponse;
import rooftop.challenge.omunoz.models.responses.GetResultResponse;
import rooftop.challenge.omunoz.models.responses.PostResponse;
import rooftop.challenge.omunoz.repositories.HashDataRepository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class TextService {

    private final EncodeDecodeService encodeDecodeService;
    private final HashDataRepository hashDataRepository;
    private IGlobalMapper globalMapper;

    public PostResponse hashText(PostPayload payload) throws Exception {

        if (payload.getText() == null){
            return null;
        }

        var chars = payload.getChars();
        var text = payload.getText();

        text = text.toLowerCase();
        chars = chars == null || chars == 0 || chars < 2 ? 2 : chars;

        payload.setChars(chars);


        var payloadHash = encodeDecodeService.encodePostPayload(payload);
        var findHashData = hashDataRepository.findByHashDataAndDeleted(payloadHash, false);

        if (findHashData != null){
            return new PostResponse(findHashData.getId(), String.format("/text/%x", findHashData.getId()));
        }

        var textLength = text.length();
        if (chars > textLength && textLength >= 2) chars = textLength;
        var coincidenceCountMap = new LinkedHashMap<String, Integer>();
        for (int i = 0; i < text.length()-1; i++) {
            var endIndex = i+chars;
            if(endIndex <= text.length()) {
                var splitString = text.substring(i, endIndex);
                if (coincidenceCountMap.get(splitString) == null){
                    var count = StringUtils.countOccurrencesOf(text, splitString);
                    coincidenceCountMap.put(splitString, count);
                }
            }
        }

        System.out.println(coincidenceCountMap);
        // Encode
        var hashDataObject = new HashData();
        hashDataObject.setHashData(payloadHash);
        hashDataObject.setChars(chars);
        hashDataObject.setResult(coincidenceCountMap);
        var hashDataSaved = hashDataRepository.save(hashDataObject);

        return new PostResponse(hashDataSaved.getId(), String.format("/text/%x", hashDataSaved.getId()));
    }

    public GetResponse getHashData(Long id){
        var hashData = hashDataRepository.findByIdAndDeleted(id, false);
        if (hashData == null) return null;
        var result = globalMapper.fromHashData(hashData);
        return result;
    }

    public List<GetResponse> getAllHashData(){
        var lstHashData = hashDataRepository.findAllByDeleted(false);
        var result = globalMapper.fromListHashData(lstHashData);
        return result;
    }

    public Boolean deleteHashData(Long id){
        var hashData = hashDataRepository.findByIdAndDeleted(id, false);
        if(hashData == null) return false;
        hashData.setDeleted(true);
        hashDataRepository.save(hashData);
        return true;
    }

}
