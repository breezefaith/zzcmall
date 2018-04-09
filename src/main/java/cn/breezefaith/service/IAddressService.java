package cn.breezefaith.service;

import java.io.IOException;

public interface IAddressService {
    boolean deleteAddress(String token, Integer aid) throws IOException;

    boolean addAddress(String token, String name, String phone, String postCode, String address) throws IOException;

    boolean updateAddress(String token, Integer aid,String name, String phone, String postCode, String address) throws IOException;
}
