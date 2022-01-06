package com.example.kingsta.service;

import com.example.kingsta.config.security.PrincipalDetails;
import com.example.kingsta.domain.image.Image;
import com.example.kingsta.domain.image.ImageRepository;
import com.example.kingsta.dto.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Transactional
@Service
public class ImageService {

//    @Value("${file.path}")
//    private static String uploadFolder;

    private static String uploadFolder = "C:/Users/wogud2/upload/";

    private final ImageRepository imageRepository;

    public void upload(ImageUploadDto imageUploadDto,@AuthenticationPrincipal PrincipalDetails principalDetails) {
        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid+"_"+imageUploadDto.getFile().getOriginalFilename();

        Path imageFilePath = Paths.get(uploadFolder+imageFileName);

        try{
            Files.write(imageFilePath,imageUploadDto.getFile().getBytes());
        } catch (Exception e){
            e.printStackTrace();
        }


        Image image = imageUploadDto.toEntity(imageFileName, principalDetails.getUser());
        imageRepository.save(image);
    }
}
