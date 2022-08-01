package com.api.EcommerceProjeto.services;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import com.api.EcommerceProjeto.domain.*;
import com.api.EcommerceProjeto.domain.enums.*;
import com.api.EcommerceProjeto.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DBService {

	@Autowired
	private ConsumidorRepository consumidorRepository;

	@Autowired
	private EmpreendedorRepository empreendedorRepository;

	@Autowired
	private LojaRepository lojarepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	public void instanciaDB() {

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = null;
		Date date2 = null;
		try{
			date1 = formato.parse("23/7/2022");
			date2 = formato.parse("2/8/2022");
		}
		catch(Exception e){
			e.printStackTrace();
		}

		Admin a1 = new Admin(null, "Palloma Gulliver", "gulliver.palloma@gmail.com", encoder.encode("123456"), "11983411107", "null","null","null","null","null","null");

		Consumidor c1 = new Consumidor(null, "Gabriel", "gabriel@gmail.com", encoder.encode("123456"),"21992934144" , "2271140",
				"Rio de Janeiro", "Rio de Janeiro", "Rio de Janeiro", "Curicica", "Segredo", "193.019.997-06");
		Consumidor c2 = new Consumidor(null, "Mingau", "mingau@gmail.com", encoder.encode("123456"), "10881823033",
				"2271140", "Rio de Janeiro", "Rio de Janeiro", "Curicica", "Segredo","segredo", "382.670.620-09");

		Empreendedor e1 = new Empreendedor(null, "Loja 1", "73.900.564/0001-54", "loja1@gmail.com", encoder.encode("123456"),
				"21988887777", Ramo.HARDWARE, "22720400", "Rio de Janeiro", "Rio de Janeiro", "Taquara", "maraville",
				"1155");
		Empreendedor e2 = new Empreendedor(null, "Loja 2", "86.005.261/0001-10", "loja2@gmail.com", encoder.encode("123456"),
				"21945451234", Ramo.SOFTWARE, "22720400", "Rio de Janeiro", "Rio de Janeiro", "Taquara", "Mio", "895");
		Empreendedor e3 = new Empreendedor(null, "Loja 2", "57.050.027/0001-06", "loja3@gmail.com", encoder.encode("123456"),
				"21992934155", Ramo.AMBOS, "22720400", "Rio de Janeiro", "Rio de Janeiro", "Taquara", "maraville",
				"1155");


		Loja l1 = new Loja(6, "#191970", "SHOPTIME", "Venda de Harware e software", e3,"teste");
		Loja l2 = new Loja(4, "#000080", "AMERICANAS", "Venda de Harware e software", e1, "teste");
		Produto p1 = new Produto(null, "Monitor Concórdia Gamer Curvo C32f 32\" 165hz 1ms Led Full Hd", 125.00, "O pior monitor do mundo", Categoria.HARDWARE, 1001, Status.ATIVO, Promocao.ATIVADA ,"https://images.tcdn.com.br/img/img_prod/740836/monitor_concordia_gamer_curvo_c32f_32_165hz_1ms_led_full_hd_6769_1_1ee122e070f295e4eb70529e293463a7.png",
				15.0, date2, l1);

		Produto p2 = new Produto(null, "Mouse Gamer Redragon - Design e performance", 105.99, "As melhores opções de Custo x Benefício: design, performance e recursos pra quem quer detonar nos Games - a Redragon tem a opção certa para você.", Categoria.HARDWARE, 1001, Status.ATIVO,Promocao.ATIVADA ,"https://static.wixstatic.com/media/71a6c2_373a28191d2243aba00ef9e50c896d5d~mv2.png/v1/crop/x_0,y_246,w_1000,h_507/fill/w_960,h_480,al_c,q_90,usm_0.66_1.00_0.01,enc_auto/71a6c2_373a28191d2243aba00ef9e50c896d5d~mv2.png",
				50.0, date1, l1);

		Produto p3 = new Produto(null, "Fone de Ouvido ", 105.99, "A melhor opção", Categoria.SOFTWARE, 1001, Status.ATIVO,Promocao.ATIVADA ,"https://images-americanas.b2w.io/produtos/3361886476/imagens/headset-fone-ouvido-gamer-rgb-usb-p3-ps4-pc-celular/3361886492_1_large.jpg",
				50.0,date1 , l2);

		adminRepository.saveAll(Arrays.asList(a1));
		consumidorRepository.saveAll(Arrays.asList(c1, c2));
		empreendedorRepository.saveAll(Arrays.asList(e1, e2, e3));
		lojarepository.saveAll(Arrays.asList(l1,l2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

	}
}