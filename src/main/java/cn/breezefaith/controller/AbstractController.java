package cn.breezefaith.controller;

import cn.breezefaith.service.IAddressService;
import cn.breezefaith.service.IItemService;
import cn.breezefaith.service.IRecordService;
import cn.breezefaith.service.IUserService;

import javax.annotation.Resource;

public abstract class AbstractController {
    @Resource(name="userService")
    protected IUserService userService;

    @Resource(name="addressService")
    protected IAddressService addressService;

    @Resource(name = "itemService")
    protected IItemService itemService;

    @Resource(name = "recordService")
    protected IRecordService recordService;
}
