package guru.springframework.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
//HTTP clients can construct HTTP multipart requests to send text or binary files to the
// server; it's mainly used for uploading files.
//Another common use-case is sending the email with an attachment.
// Multipart file requests break a large file into smaller chunks and use
// boundary markers to indicate the start and end of the block.
  /*  so with help of this MultipartFile we can upload the file in controller and then do what we want
    whether to persist it to dataBase or store it in specific path in our local machine or in the
    body of our applciation directory .*/
    void saveImageFile(Long recipeId, MultipartFile file);
}
