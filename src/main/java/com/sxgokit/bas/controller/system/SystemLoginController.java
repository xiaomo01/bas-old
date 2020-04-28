package com.sxgokit.bas.controller.system;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.sxgokit.bas.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author wgl
 * @version 1.0.0
 * @ClassName SystemAdminController.java
 * @createTime 2019年10月26日 02:20:00
 */
@RestController
@RequestMapping("system/login")
@Api(tags = {"系统用户登录"})
public class SystemLoginController extends BaseController {

    @Autowired
    private Producer producer;

    @ApiOperation(value = "获取登录图形验证码")
    @GetMapping("kaptcha.jpg")
    public void kaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        //生成文字验证码
        String text = producer.createText();
        //保存验证码到session
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, text);
        //生成图片验证码
        BufferedImage image = producer.createImage("text");
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }
}
