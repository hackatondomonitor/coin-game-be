package com.hackatonalcoolico.coingame;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;

@Slf4j
@SpringBootApplication
public class CoingameApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoingameApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void onReady() {
		log.info("　　　　　　 　　＿＿＿\n" +
				"　　　　　 　　ヽ==@==/\n" +
				"　　　　　　　（´・ω・｀）     DEPLOY THE TANK\n" +
				"　　　　　　　,_}=n==n{______、\n" +
				"　　　　　　 /,--,　　/l_l| } =（二iニニニニニニﾆO\n" +
				"　　　　　 ,/←-' ／ﾍ　.lﾉ----ﾉ ____ﾌ\n" +
				"　     ,---i-←'―‐'―‐‐`--.l-----`------、\n" +
				"　__←―i='=====i=i＿＿ﾆ l  l[i==・o  lﾆﾆl llﾆﾆl_,o_、\n" +
				" /======================----,_ヽ....ヽ--＼ヽ__     ＼\n" +
				"'-ィ'''''\"(,)'''''\"(,)'''''\"(,)'''''\"(,)''''t''ヽ‐→――――→-   ==、\n" +
				"（大）_、,_、_、,_、_、,_、,_、,_ (大）==）,,,,,,,,,,,,,,/)）==）\n" +
				"　ヽ'_i_,),i_,),i_,)i_,),i_,),i_,),i_,),i_,)>ノ==ノ￣￣'ノ==ノ\n" +
				"　　 `￣￣￣￣￣￣￣￣￣￣￣´￣￣￣￣￣￣￣￣￣´");
	}
}
