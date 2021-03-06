-- DROP TABLE ATIVO;
CREATE TABLE IF NOT EXISTS ATIVO (
  ID_ATIVO BIGINT NOT NULL,
  ATIVO VARCHAR(20) NULL
);

ALTER TABLE ATIVO ADD PRIMARY KEY (ID_ATIVO);

CREATE UNIQUE INDEX ON ATIVO (ATIVO);

CREATE SEQUENCE ATIVO_SEQ START 344;

INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(1, 'BDLL4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(2, 'BDLL3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(3, 'UPSS34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(4, 'LMTB34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(5, 'JNJB34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(6, 'FDXB34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(7, 'CATP34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(8, 'BMYB34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(9, 'BOEI34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(10, 'STBP3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(11, 'RAPT4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(12, 'RAPT3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(13, 'LUPA3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(14, 'INEP4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(15, 'INEP3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(16, 'GEPA4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(17, 'GEPA3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(18, 'FRAS3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(19, 'SHUL3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(20, 'SHUL4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(21, 'TASA3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(22, 'TASA4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(23, 'GOLL4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(24, 'GOLL11');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(25, 'ETER3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(26, 'VLID3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(27, 'TPIS3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(28, 'PTBL3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(29, 'POMO3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(30, 'POMO4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(31, 'RLOG3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(32, 'EALT3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(33, 'EALT4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(34, 'ENEV3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(35, 'WEGE3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(36, 'RAIL3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(37, 'VALE3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(38, 'USIM5');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(39, 'IVPR3B');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(40, 'IVPR4B');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(41, 'LIQO3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(42, 'CCRO3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(43, 'AZUL4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(44, 'CEDO3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(45, 'CEDO4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(46, 'NFLX34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(47, 'NIKE34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(48, 'MCDC34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(49, 'HOME34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(50, 'FDMO34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(51, 'CMCS34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(52, 'AMZO34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(53, 'RDNI3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(54, 'SLED3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(55, 'SLED4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(56, 'RSID3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(57, 'MNDL3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(58, 'LEVE3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(59, 'CTKA3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(60, 'CTKA4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(61, 'MYPK3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(62, 'GRND3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(63, 'LCAM3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(64, 'CEAB3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(65, 'LLIS3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(66, 'CGRA3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(67, 'CGRA4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(68, 'ESTR3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(69, 'ESTR4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(70, 'DIRR3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(71, 'CTNM3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(72, 'CTNM4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(73, 'ANIM3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(74, 'EVEN3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(75, 'AMAR3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(76, 'MOVI3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(77, 'JHSF3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(78, 'HBOR3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(79, 'PDGR3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(80, 'ARZZ3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(81, 'EZTC3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(82, 'HGTX3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(83, 'ALPA3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(84, 'ALPA4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(85, 'SMLS3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(86, 'RENT3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(87, 'MRVE3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(88, 'MGLU3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(89, 'LREN3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(90, 'COGN3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(91, 'WHRL4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(92, 'WHRL3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(93, 'VVAR3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(94, 'TCSA3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(95, 'SBUB34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(96, 'SEER3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(97, 'LAME3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(98, 'LAME4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(99, 'HOOT4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(100, 'GFSA3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(101, 'YDUQ3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(102, 'CYRE3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(103, 'CVCB3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(104, 'WALM34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(105, 'PGCO34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(106, 'PEPB34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(107, 'COLG34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(108, 'COCA34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(109, 'AVON34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(110, 'SMTO3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(111, 'MDIA3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(112, 'CAML3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(113, 'AGRO3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(114, 'BSEV3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(115, 'BEEF3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(116, 'BEEF11');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(117, 'VIVA3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(118, 'CRFB3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(119, 'PCAR3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(120, 'PCAR4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(121, 'NTCO3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(122, 'MRFG3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(123, 'JBSS3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(124, 'BRFS3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(125, 'BSLI3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(126, 'BSLI4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(127, 'BTTL3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(128, 'BPAR3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(129, 'WFCO34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(130, 'VISA34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(131, 'MSBR34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(132, 'MSCD34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(133, 'JPMC34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(134, 'HONB34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(135, 'GEOO34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(136, 'GSGI34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(137, 'CTGP34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(138, 'BOAC34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(139, 'MMMC34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(140, 'SCAR3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(141, 'LPSB3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(142, 'BMGB4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(143, 'IGBR3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(144, 'GSHP3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(145, 'PSSA3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(146, 'CARD3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(147, 'BBRK3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(148, 'BRPR3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(149, 'BRSR3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(150, 'BRSR5');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(151, 'BRSR6');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(152, 'BIDI3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(153, 'BIDI4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(154, 'BIDI11');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(155, 'SANB3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(156, 'SANB4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(157, 'SANB11');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(158, 'MULT3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(159, 'ITUB3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(160, 'ITUB4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(161, 'ALSO3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(162, 'LOGG3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(163, 'ITSA3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(164, 'ITSA4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(165, 'IRBR3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(166, 'IGTA3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(167, 'BBDC3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(168, 'BBDC4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(169, 'BRML3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(170, 'APER3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(171, 'BBSE3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(172, 'BPAN4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(173, 'BBAS3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(174, 'BBAS11');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(175, 'BBAS12');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(176, 'AXPB34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(177, 'RANI3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(178, 'RANI4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(179, 'FCXO34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(180, 'PMAM3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(181, 'FESA3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(182, 'FESA4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(183, 'EUCA3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(184, 'EUCA4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(185, 'SUZB3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(186, 'KLBN3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(187, 'KLBN4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(188, 'KLBN11');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(189, 'VALE5');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(190, 'UNIP3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(191, 'UNIP5');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(192, 'UNIP6');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(193, 'NEMO3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(194, 'NEMO5');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(195, 'NEMO6');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(196, 'MMXM3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(197, 'MMXM11');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(198, 'GOAU4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(199, 'CSNA3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(200, 'BRKM3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(201, 'BRKM5');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(202, 'BRAP3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(203, 'BRAP4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(204, 'ARMT34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(205, 'MAPT3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(206, 'MAPT4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(207, 'BETP3B');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(208, 'DISB34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(209, 'ATOM3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(210, 'PHMO34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(211, 'CCXC3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(212, 'SLBG34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(213, 'HALI34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(214, 'COPH34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(215, 'CHVX34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(216, 'PRIO3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(217, 'OSXB3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(218, 'DMMO3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(219, 'DMMO11');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(220, 'RPMG3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(221, 'UGPA3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(222, 'PETR3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(223, 'PETR4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(224, 'BRDT3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(225, 'EXXO34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(226, 'ENAT3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(227, 'BIOM3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(228, 'BALM3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(229, 'BALM4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(230, 'PFIZ34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(231, 'MRCK34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(232, 'GBIO33');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(233, 'PNVL3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(234, 'PNVL4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(235, 'AALR3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(236, 'ODPV3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(237, 'RADL3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(238, 'QUAL3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(239, 'OFSA3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(240, 'HYPE3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(241, 'FLRY3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(242, 'ABTT34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(243, 'TOTS3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(244, 'XRXB34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(245, 'QCOM34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(246, 'ORCL34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(247, 'MSFT34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(248, 'IBMB34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(249, 'ITLC34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(250, 'HPQB34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(251, 'EBAY34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(252, 'CSCO34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(253, 'ATTB34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(254, 'AAPL34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(255, 'LINX3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(256, 'POSI3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(257, 'VERZ34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(258, 'OIBR');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(259, 'OIBR4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(260, 'TIMP3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(261, 'VIVT3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(262, 'VIVT4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(263, 'TELB3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(264, 'TELB4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(265, 'CEPE3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(266, 'CEPE5');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(267, 'CEPE6');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(268, 'CEED3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(269, 'CEED4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(270, 'EEEL3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(271, 'EEEL4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(272, 'CASN3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(273, 'CASN4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(274, 'CEGR3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(275, 'CEBR3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(276, 'CEBR5');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(277, 'CEBR6');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(278, 'RNEW3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(279, 'RNEW4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(280, 'RNEW11');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(281, 'COCE3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(282, 'COCE5');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(283, 'COCE6');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(284, 'CLSC3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(285, 'CLSC4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(286, 'ALUP3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(287, 'ALUP4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(288, 'ALUP11');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(289, 'SAPR3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(290, 'SAPR4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(291, 'SAPR11');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(292, 'CPRE3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(293, 'CPLE3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(294, 'CPLE5');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(295, 'CPLE6');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(296, 'CPFE3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(297, 'CGAS3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(298, 'CGAS5');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(299, 'TIET3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(300, 'TIET4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(301, 'TIET11');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(302, 'NEOE3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(303, 'TRPL3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(304, 'TRPL4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(305, 'EGIE3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(306, 'TAEE3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(307, 'TAEE4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(308, 'TAEE11');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(309, 'SBSP3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(310, 'CESP3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(311, 'CESP5');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(312, 'CESP6');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(313, 'CMIG3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(314, 'CMIG4');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(315, 'AFLT3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(316, 'GOGL34');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(317, 'VULC3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(318, 'PARD3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(319, 'GOAU3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(321, 'MILS3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(322, 'SQIA3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(323, 'SGPS3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(324, 'ECOR3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(325, 'CIEL3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(326, 'BPAN3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(327, 'ROMI3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(328, 'BKBR3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(329, 'DTEX3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(330, 'CNTO3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(331, 'TGMA3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(332, 'SLCE3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(333, 'LOGN3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(334, 'JSLG3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(335, 'KROT3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(336, 'EMBR3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(337, 'GUAR3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(338, 'TRIS3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(339, 'BTOW3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(340, 'TUPY3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(341, 'MEAL3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(342, 'CRBF3');
INSERT INTO ATIVO(ID_ATIVO, ATIVO) VALUES(343, 'WIZS3');


