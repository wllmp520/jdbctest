package com.example.demo.controller;

import com.example.demo.entity.ApiResult;
import com.example.demo.entity.User;
import com.example.demo.entity.UserListForm;
import com.example.demo.entity.UserVO;
import io.github.yedaxia.apidocs.ApiDoc;
import org.springframework.web.bind.annotation.*;

/**
 * 第一个User
 * @author wl
 * @date 2020-06-16 11:11:29
 * @return null
 */
@ApiDoc
@RequestMapping("/api/user/")
@RestController
public class UserController {
    /**
     * 用户列表
     * @param listForm
     */
    @ApiDoc(value = ApiResult.class,url = "/list",method = "get")
    @RequestMapping(path = "list", method = { RequestMethod.POST}  )
    public ApiResult<UserVO> list(UserListForm listForm){
        return null;
    }

    /**
     * 保存用户
     * @param userForm
     */
    @PostMapping(path = "save")
    public ApiResult<UserVO> saveUser(@RequestBody User userForm){
        return null;
    }

    /**
     * 删除用户
     * @param userId 用户ID
     */
    @PostMapping("delete")
    public ApiResult deleteUser(@RequestParam Long userId){
        return null;
    }
}