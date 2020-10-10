package com.bluemsun.BBS.web;

import com.bluemsun.BBS.common.ServerResponse;
import com.bluemsun.BBS.entity.User;
import com.bluemsun.BBS.service.BlogService;
import com.bluemsun.BBS.service.UserService;
import com.bluemsun.BBS.util.CookieUtil;
import com.bluemsun.BBS.util.JsonUtil;
import com.bluemsun.BBS.util.RedisPoolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping(value = "/file")
public class FileController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;

    /**
     * 博客文件上传
     *
     * @param httpServletRequest
     * @param mfile
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/blogUpload.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> blogFileUpload(HttpServletRequest httpServletRequest, MultipartFile mfile) throws IOException {
        if (mfile == null) {
            return ServerResponse.createByErrorMessage("未接收到富文本上传文件！！！");
        }
        //上传的位置
        //todo 上线过后 这里需要改！！！
        String path = httpServletRequest.getSession().getServletContext().getRealPath("/uploads/");
        //判断路径是否存在
        File file = new File(path);
        if (!file.exists()) {
            //创建该文件夹
            file.mkdirs();
        }
        //获取上传文件的名称
        String filename = mfile.getOriginalFilename();
        //获得文件后缀名
        String suffix = filename.substring(filename.lastIndexOf("."));
        //新文件名
        filename = UUID.randomUUID() + suffix;
        //完成文件上传

        mfile.transferTo(new File(path, filename));
        String url = "http://bluesun.natapp1.cc/uploads/" + filename;
        return ServerResponse.createBySuccess("上传成功", url);
    }

    /**
     * 用户更新头像
     *
     * @param httpServletRequest
     * @param mfile
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/userUpload.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> userFileUpload(HttpServletRequest httpServletRequest, MultipartFile mfile) throws IOException {
        if (mfile == null) {
            return ServerResponse.createByErrorMessage("未接收到用户上传头像文件！！！");
        }
        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
        String jsonStr = RedisPoolUtil.get(loginToken);
        User user = JsonUtil.string2Obj(jsonStr,User.class);
        //上传的位置
        //todo 上线过后 这里需要改！！！
        String path = httpServletRequest.getSession().getServletContext().getRealPath("/uploads/");
        //判断路径是否存在
        File file = new File(path);
        if (!file.exists()) {
            //创建该文件夹
            file.mkdirs();
        }
        //获取上传文件的名称
        String filename = mfile.getOriginalFilename();
        //获得文件后缀名
        String suffix = filename.substring(filename.lastIndexOf("."));
        //新文件名
        filename = UUID.randomUUID() + suffix;
        //完成文件上传
        mfile.transferTo(new File(path, filename));
        String url = "http://bluesun.natapp1.cc/uploads/" + filename;
        //进行用户信息更新
        User user1 = new User();
//        user1.setUserId(13);
        user1.setUserId(user.getUserId());
        user1.setProfileImg(url);
        ServerResponse<User> response = userService.updateUserImg(user1);
        return response;
    }
}