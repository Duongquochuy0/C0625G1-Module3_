package com.example.thi_thu.service;

import com.example.thi_thu.entity.XuatXu;
import com.example.thi_thu.repository.IXuatXuRepository;
import com.example.thi_thu.repository.XuatXuRepository;

import java.util.List;

public class XuatXuService implements IXuatXuService {
    private final IXuatXuRepository xuatXuRepository = new XuatXuRepository();

    @Override
    public List<XuatXu> findAll() {
        return xuatXuRepository.findAll();
    }
}
