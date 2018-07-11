package com.ricardococati.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface IGerenciadorArquivosService {

	public void moverArquivosEntreDiretorios(String origem, String destino) throws Exception, IOException, FileNotFoundException;
	
	public void moverArquivosEntreDiretoriosVerificaDiretorio(String origem, String destino) throws Exception, IOException, FileNotFoundException;

	public void moveArquivoParaDiretorio(File diretorioDestino, File arquivo) throws Exception, IOException, FileNotFoundException;
	
	public File renomearArquivo(File arquivo);

	public boolean validarArquivoLock(File arquivo);

	public void removeLock(File diretorioDestino, File arquivo);

}
