package com.tanhua.server.controller;

import com.tanhua.server.service.TaoHuaService;
import com.tanhua.server.vo.SoundVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("peachblossom")
public class TaoHuaController {
    @Autowired
    private TaoHuaService taoHuaService;

    @PostMapping
    public Object sendSound(@RequestParam("soundFile") MultipartFile soundFile) {
        return taoHuaService.sendSound(soundFile);
    }

    @GetMapping
    public SoundVo querySound() {
        return this.taoHuaService.querySound();
    }

}
