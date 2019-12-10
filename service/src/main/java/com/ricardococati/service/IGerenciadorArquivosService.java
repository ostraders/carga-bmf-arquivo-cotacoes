package com.ricardococati.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface IGerenciadorArquivosService {

	void moverArquivosEntreDiretorios(String origem, String destino) throws Exception, IOException, FileNotFoundException;
	
	void moverArquivosEntreDiretoriosVerificaDiretorio(String origem, String destino) throws Exception, IOException, FileNotFoundException;

	void moveArquivoParaDiretorio(File diretorioDestino, File arquivo) throws Exception, IOException, FileNotFoundException;
	
	File renomearArquivo(File arquivo);

	Boolean validarArquivoLock(File arquivo);

	void removeLock(File diretorioDestino, File arquivo);

}
