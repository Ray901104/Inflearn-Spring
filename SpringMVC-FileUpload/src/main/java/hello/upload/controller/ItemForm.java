package hello.upload.controller;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ItemForm {

    private Long itemId;
    private String itemName;
    private MultipartFile attachFile; // 사용자가 업로드하는 파일은 MultipartFile
    private List<MultipartFile> imageFiles;
}
