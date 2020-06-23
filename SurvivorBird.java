package com.ferhatiltas.survivorbird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;

import java.util.Random;

public class SurvivorBird extends ApplicationAdapter {
		SpriteBatch batch;
		Texture background;
		Texture bird;
		Texture knife1;
		Texture knife2;
		Texture knife4;
		Texture knife5;
		float birdX=0;
		float birdY=0;
		int gameState;
		float velocity=-1;//-1
		float gravity=0.1f;
		int numberOfEnemies=8;
		float[] enemyX=new float[numberOfEnemies];
		float[] enemyOffSet=new float[numberOfEnemies];
		float[] enemyOffSet2=new float[numberOfEnemies];
		float[] enemyOffSet3=new float[numberOfEnemies];
		float distance=0;
		Random random;
		float enemyVelocity=4;//bıçak hızı
		ShapeRenderer shapeRenderer;
		Circle birdCircle;
		Circle[] enemyCircles;
		Circle[] enemyCircles2;
		Circle[] enemyCircles3;
		int score=0;
		int scoreEnemy=0;
		BitmapFont font;
		BitmapFont font2;

	@Override
	public void create () {
		shapeRenderer=new ShapeRenderer();

		random=new Random();
		batch=new SpriteBatch();
		background=new Texture("background.png");
		bird=new Texture("bird.png");
		knife1=new Texture("knife.png");
		knife2=new Texture("knife2.png");
		knife4=new Texture("knife.png");
		knife5=new Texture("knife3.png");
		distance=Gdx.graphics.getWidth()/2;
		birdX=Gdx.graphics.getWidth()/8;
		birdY=Gdx.graphics.getHeight()/3;

		font=new BitmapFont();
		font.setColor(Color.CYAN);
		font.getData().setScale(5);

		font2=new BitmapFont();
		font2.setColor(Color.CYAN);
		font2.getData().setScale(3);



		birdCircle=new Circle();
		enemyCircles=new Circle[numberOfEnemies];
		enemyCircles2=new Circle[numberOfEnemies];
		enemyCircles3=new Circle[numberOfEnemies];

		for (int i=0;i<numberOfEnemies;i++){

			enemyOffSet[i]=(random.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);
			enemyOffSet2[i]=(random.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);
			enemyOffSet3[i]=(random.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);

			enemyX[i]=Gdx.graphics.getWidth()- knife1.getWidth()/2 +i*distance;

			enemyCircles[i]=new Circle();
			enemyCircles2[i]=new Circle();
			enemyCircles3[i]=new Circle();
		}
	}

	@Override
	public void render () {
		batch.begin();
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


		if (gameState == 1) {
			if (enemyX[scoreEnemy] < Gdx.graphics.getWidth() / 4) {
				score++;
				if (scoreEnemy < numberOfEnemies - 1) {
						scoreEnemy++;
				}else {
					scoreEnemy=0;
				}
			}

			if (Gdx.input.justTouched()) {
				velocity = -23;//-23

			}

			for (int i = 0; i < numberOfEnemies; i++) {

				if (enemyX[i] < Gdx.graphics.getWidth() / 10) {//10
					enemyX[i] = enemyX[i] + numberOfEnemies * distance;

					enemyOffSet[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 100);//200
					enemyOffSet2[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 100);
					enemyOffSet3[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 100);
				} else {
					enemyX[i] = enemyX[i] - enemyVelocity;
				}
								//enemyX[i]
				batch.draw(knife1, enemyX[i], Gdx.graphics.getHeight() / 2 + enemyOffSet[i], Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 8);//10//8
			//	batch.draw(knife2, 900, Gdx.graphics.getHeight() / 2+ enemyOffSet2[i], Gdx.graphics.getWidth() / 28, Gdx.graphics.getHeight() / 8);
			//	batch.draw(knife4, 900, Gdx.graphics.getHeight() / 2 + enemyOffSet3[i], Gdx.graphics.getWidth() / 28, Gdx.graphics.getHeight() / 8);
			//	batch.draw(knife5, enemyX[i], Gdx.graphics.getHeight() / 2 + enemyOffSet2[i], Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 8);

				enemyCircles[i]=new Circle(enemyX[i]+Gdx.graphics.getWidth() / 20,Gdx.graphics.getHeight() / 2 +enemyOffSet[i]+Gdx.graphics.getHeight()/16,Gdx.graphics.getWidth() / 20);
				enemyCircles2[i]=new Circle(enemyX[i]+Gdx.graphics.getWidth() / 20,Gdx.graphics.getHeight() / 2 +enemyOffSet2[i]+Gdx.graphics.getHeight()/16,Gdx.graphics.getWidth() / 20);
				enemyCircles3[i]=new Circle(enemyX[i]+Gdx.graphics.getWidth() / 20,Gdx.graphics.getHeight() / 2 +enemyOffSet3[i]+Gdx.graphics.getHeight()/16,Gdx.graphics.getWidth() / 20);
											//enemyX[i]+Gdx.graphics.getWidth() / 20
			}

			if (birdY > 0 ) {
				velocity++; //kuşun yere düşme hıızzı
				birdY = birdY - velocity;
			}else {
				gameState=2;
			}

		} else if (gameState==0){
			if (Gdx.input.justTouched()) {
				gameState = 1;
			}
		} else if (gameState == 2) {

			font2.draw(batch,"Game over. Click here to play again.",50,Gdx.graphics.getHeight()/2);
			if (Gdx.input.justTouched()) {
				gameState = 1;
				birdY=Gdx.graphics.getHeight()/3;

				for (int i=0;i<numberOfEnemies;i++){

					enemyOffSet[i]=(random.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);
					enemyOffSet2[i]=(random.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);
					enemyOffSet3[i]=(random.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);

					enemyX[i]=Gdx.graphics.getWidth()- knife1.getWidth()/2 +i*distance;

					enemyCircles[i]=new Circle();
					enemyCircles2[i]=new Circle();
					enemyCircles3[i]=new Circle();
				}
				velocity=0;
				scoreEnemy=0;
				score=0;
			}
		}


		batch.draw(bird, birdX, birdY, Gdx.graphics.getWidth() / 6, Gdx.graphics.getHeight() / 4);

		font.draw(batch,String.valueOf("Point: "+score),20,110);
		batch.end();

		birdCircle.set(birdX+Gdx.graphics.getWidth() / 10, birdY+Gdx.graphics.getHeight() / 8, Gdx.graphics.getWidth() / 50);

	//	shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		//shapeRenderer.setColor(Color.BLACK);
	//	shapeRenderer.circle(birdCircle.x,birdCircle.y,birdCircle.radius);
		for (int i=0;i<numberOfEnemies;i++){
	//	shapeRenderer.circle(enemyX[i]+Gdx.graphics.getWidth() / 20,Gdx.graphics.getHeight() / 2 +enemyOffSet[i]+Gdx.graphics.getHeight()/16,Gdx.graphics.getWidth() / 50);
	//		shapeRenderer.circle(enemyX[i]+Gdx.graphics.getWidth() / 20,Gdx.graphics.getHeight() / 2 +enemyOffSet2[i]+Gdx.graphics.getHeight()/16,Gdx.graphics.getWidth() / 40);
	//		shapeRenderer.circle(enemyX[i]+Gdx.graphics.getWidth() / 20,Gdx.graphics.getHeight() / 2 +enemyOffSet3[i]+Gdx.graphics.getHeight()/16,Gdx.graphics.getWidth() / 40);

			if (Intersector.overlaps(birdCircle,enemyCircles[i])){
				gameState=2;
							}
		}
	//	shapeRenderer.end();

	}

	@Override
	public void dispose () {

	}
}
