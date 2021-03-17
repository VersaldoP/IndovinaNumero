package it.polito.tdp.IndovinaNumero.model;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

public class Model {
	private final int NMAX = 100;
	private final int TMAX = 8;
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco = false;
	private Set<Integer> setT;
	
	
	
	
	public void nuovaPartita() {
		//gestione inizio nuova partita
    	this.segreto = (int) (Math.random() * NMAX) +1;
    	this.tentativiFatti = 0;
    	this.inGioco = true;
    	this.setT = new HashSet<>();
//    	System.out.println(" "+segreto+"");
		
	}
	public int tentativo(int tentativo) {
		// controllare se la partita è in corso 
		if(this.tentativiFatti==TMAX) {
			this.inGioco=false;	
		}
		
		if(!inGioco) {
			throw new IllegalStateException("La partita non è ancora in corso");
		}
		if(!tentativoValido(tentativo)) {
		throw new InvalidParameterException("La partita non è ancora in corso");
		}
		// il tentativo è valido 
		
		
		this.tentativiFatti ++;
		this.setT.add(tentativo);
		
		if(tentativo==segreto) {
			return 0;
		}
		if(tentativo<segreto) {
			return -1;
		}
		else {
			return 1;
		}
		
		
	}
	private boolean tentativoValido(int tentativo) {
		if (tentativo<1||tentativo>NMAX||setT.contains(tentativo)) {
		
				return false;	
		}else {
			return true;
			
		}
		
		
	}

	public int getSegreto() {
		return segreto;
	}

	
	public int getTentativiFatti() {
		return tentativiFatti;
	}


	public boolean isInGioco() {
		return inGioco;
	}

	public int getNMAX() {
		return NMAX;
	}

	public int getTMAX() {
		return TMAX;
	}
	

}
