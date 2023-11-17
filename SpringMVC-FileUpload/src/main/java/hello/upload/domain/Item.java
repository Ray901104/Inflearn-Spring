package hello.upload.domain;

import lombok.Data;

import java.util.List;

@Data
public class Item {

    private Long id;
    private String itemName;
    private UploadFile attachFile; // 사용자가 저장되는 파일을 객체 데이터에 저장할때는 만들어둔 UploadFile
    private List<UploadFile> imageFiles;
}
