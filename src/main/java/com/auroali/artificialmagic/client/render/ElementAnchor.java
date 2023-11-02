package com.auroali.artificialmagic.client.render;

public enum ElementAnchor {
	TOP_LEFT,
	TOP_RIGHT,
	BOTTOM_LEFT,
	BOTTOM_RIGHT;

	public int getX(int screenWidth, int padding) {
		return this == TOP_LEFT || this == BOTTOM_LEFT ? padding : screenWidth - padding;
	}

	public int getY(int screenHeight, int padding) {
		return this == TOP_LEFT || this == TOP_RIGHT ? padding : screenHeight - padding;
	}

	public int getX(int screenWidth) {
		return getX(screenWidth, 0);
	}

	public int getY(int screenHeight) {
		return getY(screenHeight, 0);
	}

	public boolean isLeft() {
		return this == TOP_LEFT || this == BOTTOM_LEFT;
	}

	public boolean isRight() {
		return !isLeft();
	}

	public boolean isBottom() {
		return this == BOTTOM_LEFT || this == BOTTOM_RIGHT;
	}

	public boolean isTop() {
		return !isBottom();
	}
}
