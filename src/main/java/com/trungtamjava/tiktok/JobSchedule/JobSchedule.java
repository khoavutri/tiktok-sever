package com.trungtamjava.tiktok.JobSchedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;


@Component
@Log4j2
public class JobSchedule {
	@Scheduled(fixedDelay = 6000000)
	public void khoa() {
		
	
	}
}
