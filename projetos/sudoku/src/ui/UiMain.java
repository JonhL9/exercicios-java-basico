package ui;

import static java.util.stream.Collectors.toMap;

import java.util.stream.Stream;

import ui.custom.screen.MainScreen;

public class UiMain {


	public static void main(String[] args) {
		final var gameConfig = Stream.of(args)
                .collect(toMap(k -> k.split(";")[0], v -> v.split(";")[1]));
		var mainScreen = new MainScreen(gameConfig);
		mainScreen.buildMainScreen();
	}

}
