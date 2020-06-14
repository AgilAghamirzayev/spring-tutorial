package app.controller;

import app.service.GoogleDriveService;
import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/file")
@Controller
public class FileController {

    private final GoogleDriveService googleDrive;

    public FileController(GoogleDriveService googleDrive) {
        this.googleDrive = googleDrive;
    }

    @GetMapping
    public String handleGET() {
        return "form_upload";
    }

    @SneakyThrows
    @ResponseBody
    @PostMapping()
    public String handlePOST(@RequestParam("filecontents") MultipartFile file) {
        final byte[] bytes = file.getBytes();

        googleDrive.uploadFile(bytes);

        String contents = new String(bytes);
        return String.format("file %s got: %s", file.getOriginalFilename(), contents);

    }

    @GetMapping("download/1")
    public ResponseEntity<Resource> handleDownload() {
        final Resource file = new ClassPathResource("abc.txt");

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        String.format("attachment; filename=\"%s\"", file.getFilename())
                ).body(file);
    }

    @GetMapping("dl/{filename:.+}")
    public ResponseEntity<Resource> handleDownloadByNamw(@PathVariable String filename) {
        Resource file = new ClassPathResource(filename);

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        String.format("attachment; filename=\"%s\"", file.getFilename())
                ).body(file);

    }
}
