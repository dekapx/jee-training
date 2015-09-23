package com.ericsson.trainings.gof.patterns.builder;

public class TestBuilder {
	public static void main(String[] args) {
		new Computer.ComputerBuilder("500 GB", "2 GB").setBluetoothEnabled(true).setGraphicsCardEnabled(true).build();
	}
}
