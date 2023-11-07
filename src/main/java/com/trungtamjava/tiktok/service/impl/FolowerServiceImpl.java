package com.trungtamjava.tiktok.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trungtamjava.tiktok.dao.impl.FolowerDaoImpl;
import com.trungtamjava.tiktok.dao.impl.UserDaoImpl;
import com.trungtamjava.tiktok.entity.Folower;
import com.trungtamjava.tiktok.entity.User;
import com.trungtamjava.tiktok.model.FolowerDto;
import com.trungtamjava.tiktok.model.UserDto;
import com.trungtamjava.tiktok.service.FolowerService;

@Service
public class FolowerServiceImpl implements FolowerService {
	@Autowired
	FolowerDaoImpl folowerDaoImpl;
	@Autowired
	UserDaoImpl userDaoImpl;
	@Override
	public List<FolowerDto> SelectListAll() {
		// TODO Auto-generated method stub
		List<FolowerDto>folowerDtos = new ArrayList<FolowerDto>();
		List<Folower> folowers = folowerDaoImpl.SelectListAll();
		if (folowers!=null) {
		for (Folower folower:folowers) {
			FolowerDto folowerDto = new FolowerDto();
			folowerDto.setId(folower.getId());
			folowerDto.setNguoiFolowId(folower.getNguoiFolow().getId());
			folowerDto.setDuocFolowId(folower.getDuocFolow().getId());
			folowerDtos.add(folowerDto);
		}
		return folowerDtos;} else return null;
	}

	@Override
	public List<FolowerDto> SelectPageAll(int currentPage, int size) {
		// TODO Auto-generated method stub
		List<FolowerDto>folowerDtos = new ArrayList<FolowerDto>();
		List<Folower> folowers = folowerDaoImpl.SelectPageAll(currentPage,size).getContent();
		if (folowers!=null) {
		for (Folower folower:folowers) {
			FolowerDto folowerDto = new FolowerDto();
			folowerDto.setId(folower.getId());
			folowerDto.setNguoiFolowId(folower.getNguoiFolow().getId());
			folowerDto.setDuocFolowId(folower.getDuocFolow().getId());
			folowerDtos.add(folowerDto);
		}
		return folowerDtos;} else return null;
	}

	@Override
	public List<FolowerDto> SelectListByNguoiFolow(UserDto userDto) {
		// TODO Auto-generated method stub
		User user = userDaoImpl.SearchById(userDto.getId());
		List<FolowerDto>folowerDtos = new ArrayList<FolowerDto>();
		List<Folower> folowers = folowerDaoImpl.SelectListByNguoiFolow(user);
		if (folowers!=null) {
		for (Folower folower:folowers) {
			FolowerDto folowerDto = new FolowerDto();
			folowerDto.setId(folower.getId());
			folowerDto.setNguoiFolowId(folower.getNguoiFolow().getId());
			folowerDto.setDuocFolowId(folower.getDuocFolow().getId());
			folowerDtos.add(folowerDto);
		}
		return folowerDtos;} else return null;
	}

	@Override
	public List<FolowerDto> SelectListByDuocFolow(UserDto userDto) {
		// TODO Auto-generated method stub
		User user = userDaoImpl.SearchById(userDto.getId());
		List<FolowerDto>folowerDtos = new ArrayList<FolowerDto>();
		List<Folower> folowers = folowerDaoImpl.SelectListByDuocFolow(user);
		if (folowers!=null) {
		for (Folower folower:folowers) {
			FolowerDto folowerDto = new FolowerDto();
			folowerDto.setId(folower.getId());
			folowerDto.setNguoiFolowId(folower.getNguoiFolow().getId());
			folowerDto.setDuocFolowId(folower.getDuocFolow().getId());
			folowerDtos.add(folowerDto);
		}
		return folowerDtos;} else return null;
	}

	@Override
	public List<FolowerDto> SelectPageByNguoiFolow(UserDto userDto, int currentPage, int size) {
		// TODO Auto-generated method stub
		User user = userDaoImpl.SearchById(userDto.getId());
		List<FolowerDto>folowerDtos = new ArrayList<FolowerDto>();
		List<Folower> folowers =
				folowerDaoImpl.SelectPageByNguoiFolow(user, currentPage, size).getContent();
		if (folowers!=null) {
		for (Folower folower:folowers) {
			FolowerDto folowerDto = new FolowerDto();
			folowerDto.setId(folower.getId());
			folowerDto.setNguoiFolowId(folower.getNguoiFolow().getId());
			folowerDto.setDuocFolowId(folower.getDuocFolow().getId());
			folowerDtos.add(folowerDto);
		}
		return folowerDtos;} else return null;
	}

	@Override
	public List<FolowerDto> SelectPageByDuocFolow(UserDto userDto, int currentPage, int size) {
		// TODO Auto-generated method stub
		User user = userDaoImpl.SearchById(userDto.getId());
		List<FolowerDto>folowerDtos = new ArrayList<FolowerDto>();
		List<Folower> folowers =
				folowerDaoImpl.SelectPageByDuocFolow(user, currentPage, size).getContent();
		for (Folower folower:folowers) {
			FolowerDto folowerDto = new FolowerDto();
			folowerDto.setId(folower.getId());
			folowerDto.setNguoiFolowId(folower.getNguoiFolow().getId());
			folowerDto.setDuocFolowId(folower.getDuocFolow().getId());
			folowerDtos.add(folowerDto);
		}
		return folowerDtos;
	}

	@Override
	public int folower(UserDto userDto) {
		// TODO Auto-generated method stub
		User user = userDaoImpl.SearchById(userDto.getId());
		return folowerDaoImpl.folower(user);
	}

	@Override
	public int dangFolow(UserDto userDto) {
		// TODO Auto-generated method stub
		User user = userDaoImpl.SearchById(userDto.getId());
		return folowerDaoImpl.dangFolow(user);
	}

	@Override
	public FolowerDto selectById(int id) {
		Folower folower =folowerDaoImpl.selectById(id);
		FolowerDto folowerDto = new FolowerDto();
		folowerDto.setId(id);
		folowerDto.setNguoiFolowId(folower.getNguoiFolow().getId());
		folowerDto.setDuocFolowId(folower.getId());
		return folowerDto;
	}

	@Override
	public void insert(FolowerDto folowerDto) {
		// TODO Auto-generated method stub
		Folower folower = new Folower();
		folower.setId(0);
		folower.setNguoiFolow(userDaoImpl.SearchById(folowerDto.getNguoiFolowId()));
		folower.setDuocFolow(userDaoImpl.SearchById(folowerDto.getDuocFolowId()));
		folowerDaoImpl.insert(folower);
	}

	@Override
	public void upDate(FolowerDto folowerDto) {
		// TODO Auto-generated method stub
		Folower folower = new Folower();
		folower.setId(folowerDto.getId());
		folower.setNguoiFolow(userDaoImpl.SearchById(folowerDto.getNguoiFolowId()));
		folower.setDuocFolow(userDaoImpl.SearchById(folowerDto.getDuocFolowId()));
		folowerDaoImpl.upDate(folower);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		folowerDaoImpl.deleteById(id);
	}

	@Override
	public FolowerDto kiemtra(UserDto userDto1, UserDto userDto2) {
		User user1 = userDaoImpl.SearchById(userDto1.getId());
		User user2 = userDaoImpl.SearchById(userDto2.getId());
		Folower folower = folowerDaoImpl.kt(user1, user2);
		if (folower!=null) {
			FolowerDto folowerDto = new FolowerDto();
			folowerDto.setId(folower.getId());
			folowerDto.setDuocFolowId(folower.getDuocFolow().getId());
			folowerDto.setNguoiFolowId(folower.getNguoiFolow().getId());
			return folowerDto;
		}
		return null;
	}

}
