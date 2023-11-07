package com.trungtamjava.tiktok.service;

import java.util.List;


import com.trungtamjava.tiktok.model.FolowerDto;
import com.trungtamjava.tiktok.model.UserDto;

public interface FolowerService {
	public List<FolowerDto>SelectListAll();
	public List<FolowerDto>SelectPageAll(int currentPage,int size);
	public List<FolowerDto> SelectListByNguoiFolow(UserDto userDto);
	public List<FolowerDto> SelectListByDuocFolow(UserDto userDto);
	public List<FolowerDto>SelectPageByNguoiFolow(UserDto userDto,int currentPage,int size);
	public List<FolowerDto>SelectPageByDuocFolow(UserDto userDto,int currentPage,int size);
	public int folower(UserDto userDto);
	public int dangFolow(UserDto userDto);
	public FolowerDto selectById(int id);
	public void insert(FolowerDto folowerDto);
	public void upDate(FolowerDto folowerDto);
	public void deleteById(int id);
	public FolowerDto kiemtra(UserDto userDto1,UserDto userDto2);
}
