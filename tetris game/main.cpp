#include <GL/glut.h>
#include <stdio.h>
#include <string.h>
#include <math.h>

int xReverseZ = 250;
int yReverseZ = 800;
int yRegularZ = 800;
int xRegularZ = 250;
int xSquare = 250;
int ySquare = 800;
int xRegularL = 250;
int yRegularL = 800;
int xReverseL = 250;
int yReverseL = 800;
int xTower = 250;
int yTower = 800;
int xBox = 250;
int yBox = 800;
int start = 0;
int randomShape ;
int row;
int column;
int field[10][16];
int square = 0;
int regularZ = 0;
int reverseZ = 0;
int regularL = 0;
int reverseL = 0;
int tower = 0;
int rotateReverseZ = 0;
int score = 0;
int box = 0;
//double rotateReverse = 0;

//methods decleration;
int getRow(int);
void print(int,int,char*);
void keyBoard(unsigned char key, int a, int b);

//<<<<<<<<<<<<<<<<<<<<<<<< myDisplay >>>>>>>>>>>>>>>>>

void myDisplay()
{
glClear(GL_COLOR_BUFFER_BIT);
char * stC[100];
sprintf((char *)stC,"score = %d",score);
print(0,550,(char *)stC);
	// The regular z shape
	if(start == 1) {
		if(regularZ == 1) {
	glPushMatrix();
	xRegularZ = 250;
	glRotated(rotateReverseZ,0,0,1);
	glBegin(GL_QUADS);
	glTranslated(xRegularZ,500,0);
	glColor3f(1.0f,0.0f,0.0f);
	int final = xRegularZ + 100;
	while(xRegularZ <= final) {
		glVertex3i(xRegularZ,yRegularZ,0);
		glVertex3i(xRegularZ + 50,yRegularZ,0);
		glVertex3i(xRegularZ + 50,yRegularZ+ 50,0);
		glVertex3i(xRegularZ,yRegularZ + 50,0);
		xRegularZ = xRegularZ + 50;
	}
	xRegularZ = xRegularZ - 200;
	final = xRegularZ+ 100;
	while(xRegularZ <= final) {
			glVertex3i(xRegularZ,yRegularZ+ 50,0);
			glVertex3i(xRegularZ + 50,yRegularZ + 50,0);
			glVertex3i(xRegularZ + 50,yRegularZ + 100,0);
			glVertex3i(xRegularZ,yRegularZ + 100,0);
			xRegularZ = xRegularZ + 50;
	}
	xRegularZ = 250;
	glEnd();
	glPopMatrix();
	}
	//The square shape
	if(square == 1) {
	glPushMatrix();
	xSquare = 250;
	glBegin(GL_QUADS);
	glTranslated(xSquare,500,0);
	glColor3f(0.0f,1.0f,1.0f);
	xSquare = 150;
	int final = xSquare + 200;
	while(xSquare <= final) {
			glVertex3i(xSquare ,ySquare,0);
			glVertex3i(xSquare + 50,ySquare,0);
			glVertex3i(xSquare + 50,ySquare + 50,0);
			glVertex3i(xSquare,ySquare + 50,0);
			xSquare = xSquare+ 50;
		}
	xSquare = 250;
	glEnd();
	glPopMatrix();
	}

	//The reverse Z-shape
	if(reverseZ == 1) {
		glPushMatrix();
		xReverseZ = 250;
		//glRotated(rotateReverse,0,0,1);
		glBegin(GL_QUADS);
		glTranslated(xReverseZ,yReverseZ,0);
		glColor3f(0.0f,1.0f,0.0f);
		xReverseZ = 200;
		int final = xReverseZ + 100;
		while(xReverseZ <= final) {
			glVertex3i(xReverseZ,yReverseZ,0);
			glVertex3i(xReverseZ + 50,yReverseZ,0);
			glVertex3i(xReverseZ + 50,yReverseZ+ 50,0);
			glVertex3i(xReverseZ,yReverseZ + 50,0);
			xReverseZ = xReverseZ + 50;
		}
		xReverseZ = xReverseZ - 100;
		final = xReverseZ + 100;
		while(xReverseZ <= final) {
				glVertex3i(xReverseZ,yReverseZ + 50,0);
				glVertex3i(xReverseZ + 50,yReverseZ + 50,0);
				glVertex3i(xReverseZ + 50,yReverseZ + 100,0);
				glVertex3i(xReverseZ,yReverseZ + 100,0);
				xReverseZ = xReverseZ + 50;
		}
		xReverseZ = 250;
		glEnd();
		glPopMatrix();
		}

	//The regular L shape
	if(regularL == 1){
		glPushMatrix();
		xRegularL = 250;
		//glRotated(rotateReverse,0,0,1);
		glBegin(GL_QUADS);
		glTranslated(xRegularL,500,0);
		glColor3f(0.0f,0.0f,1.0f);
		xRegularL = 150;
		int final = xRegularL + 150;
		while(xRegularL <= final){
			glVertex3i(xRegularL,yRegularL,0);
			glVertex3i(xRegularL + 50,yRegularL ,0);
			glVertex3i(xRegularL + 50,yRegularL + 50,0);
			glVertex3i(xRegularL,yRegularL + 50,0);
			xRegularL = xRegularL + 50;
		}
		xRegularL = xRegularL - 200;
		final = xRegularL + 50;
		while(xRegularL <= final){
			glVertex3i(xRegularL,yRegularL + 50,0);
			glVertex3i(xRegularL + 50,yRegularL + 50,0);
			glVertex3i(xRegularL + 50,yRegularL + 100,0);
			glVertex3i(xRegularL,yRegularL + 100,0);
			xRegularL = xRegularL + 50;
		}
		xRegularL = 250;
		glEnd();
		glPopMatrix();
	}


		//the tower shape
		if(tower == 1) {
			glPushMatrix();
			xTower = 250;
			//glRotated(rotateReverse,0,0,1);
			glBegin(GL_QUADS);
			glTranslated(xTower,500,0);
			glColor3f(1.0f,0.0f,1.0f);
			xTower = 200;
			int final = xTower + 150;
			while(xTower <= final) {
				glVertex3i(xTower,yTower,0);
				glVertex3i(xTower + 50,yTower,0);
				glVertex3i(xTower + 50,yTower + 50,0);
				glVertex3i(xTower,yTower + 50,0);
				xTower = xTower + 50;
			}
			xTower = xTower - 150;
			final = xTower + 50;
			while(xTower <= final) {
				glVertex3i(xTower,yTower + 50,0);
				glVertex3i(xTower + 50,yTower + 50,0);
				glVertex3i(xTower + 50,yTower + 100,0);
				glVertex3i(xTower,yTower + 100,0);
				xTower = xTower + 50;
			}
			xTower = 250;
			glEnd();
			glPopMatrix();
		}

		//the box shape
		if(box == 1) {
			glPushMatrix();
			xBox = 250;
			//glRotated(rotateReverse,0,0,1);
			glBegin(GL_QUADS);
			glTranslated(xBox,500,0);
			glColor3f(1.0f,1.0f,0.0f);
			xBox = 200;
			int final = xBox + 100;
			while(xBox <= final) {
				glVertex3i(xBox,yBox,0);
				glVertex3i(xBox + 50,yBox,0);
				glVertex3i(xBox + 50,yBox + 50,0);
				glVertex3i(xBox,yBox+ 50,0);
				xBox = xBox + 50;
			}
			xBox = xBox - 150;
			final = xBox + 100;
			while(xBox <= final) {
				glVertex3i(xBox,yBox + 50,0);
				glVertex3i(xBox + 50,yBox + 50,0);
				glVertex3i(xBox + 50,yBox + 100,0);
				glVertex3i(xBox,yBox + 100,0);
				xBox = xBox + 50;
			}
			xBox = 250;
			glEnd();
			glPopMatrix();
		}
		//The reverse L shape
			if(reverseL == 1){
					glPushMatrix();
					xReverseL = 250;
					//glRotated(rotateReverse,0,0,1);
					glBegin(GL_QUADS);
					glTranslated(xReverseL,500,0);
					glColor3f(1.0f,0.5f,0.0f);
					xReverseL = 150;
					int final = xReverseL + 150;
					while(xReverseL <= final){
						glVertex3i(xReverseL,yReverseL,0);
						glVertex3i(xReverseL + 50,yReverseL ,0);
						glVertex3i(xReverseL + 50,yReverseL + 50,0);
						glVertex3i(xReverseL,yReverseL + 50,0);
						xReverseL = xReverseL + 50;
					}
					xReverseL = xReverseL - 100;
					final = xReverseL+ 50;
					while(xReverseL <= final){
						glVertex3i(xReverseL,yReverseL + 50,0);
						glVertex3i(xReverseL + 50,yReverseL + 50,0);
						glVertex3i(xReverseL + 50,yReverseL + 100,0);
						glVertex3i(xReverseL,yReverseL + 100,0);
						xReverseL = xReverseL + 50;
					}
					xReverseL = 250;
					glEnd();
					glPopMatrix();
				}




	}


glFlush();


}

void anim()
{
	glClear(GL_COLOR_BUFFER_BIT);
	if(regularZ == 1) {
	row = getRow(yRegularZ);
	column = xRegularZ / 50;
	if(yRegularZ > 0 && field[row][column] == 0 && field[row][column + 1] == 0
			&& field[row - 1][column - 1] == 0) {
		yRegularZ = yRegularZ - 50;
	}
	else {
		field[row - 1][column] = 1;
		field[row - 1][column + 1] = 1;
		field[row - 2][column - 1] = 1;
		field[row - 2][column - 1] = 1;
		square = 1;
	}
	}

	if(square == 1) {
		row = getRow(ySquare);
		column = (xSquare / 50) - 2;
		if(ySquare > 0 && field[row][column] == 0 && field[row][column + 1] == 0
					&& field[row][column + 2] == 0 && field[row][column + 3] == 0) {
				ySquare = ySquare - 50;
			}
			else {
				field[row - 1][column] = 1;
				field[row - 1][column + 1] = 1;
				field[row - 1][column + 2] = 1;
				field[row - 1][column + 3] = 1;
				reverseZ = 1;
			}
	}
	if(reverseZ == 1) {
		row = getRow(yReverseZ);
		column = (xReverseZ / 50) - 2;
		if(yReverseZ > 0 && field[row][column] == 0 && field[row][column + 1] == 0
			&& field[row - 1][column + 2] == 0 ) {
			yReverseZ= yReverseZ - 50;
		}
		else{
			field[row - 1][column] = 1;
			field[row - 1][column + 1] = 1;
			field[row - 2][column + 1] = 1;
			field[row - 2][column + 2] = 1;
			regularL = 1;
		}
	}
	if(regularL == 1) {
		row = getRow(yRegularL) - 1;
		column = (xRegularL / 50) - 2;
		if(yRegularL > 0 && field[row][column] == 0 && field[row][column + 1] == 0
			&& field[row][column + 2] == 0 ) {
			yRegularL = yRegularL - 50;
		}
		else {
			field[row - 1][column] = 1;
			field[row - 1][column + 1] = 1;
			field[row - 1][column + 2] = 1;
			field[row - 2][column] = 1;
			tower = 1;
		}
	}

	if(tower == 1) {
		row = getRow(yTower);
		column = (xTower / 50) - 1;
		if(yTower > 0 && field[row][column] == 0 && field[row][column + 1] == 0
				&& field[row][column + 2] == 0) {
			yTower = yTower - 50;
		}
		else {
			field[row - 1][column] = 1;
			field[row - 1][column + 1] = 1;
			field[row - 1][column + 2] = 1;
			field[row - 2][column + 1] = 1;
			box = 1;
		}
	}
	if(box == 1) {
		row = getRow(yBox);
		column = (xBox / 50) - 1;
		if(yBox > 0 && field[row][column] == 0 && field[row][column + 1] == 0) {
			yBox = yBox - 50;
		}
		else {
			field[row - 1][column] = 1;
			field[row - 1][column + 1] = 1;
			field[row - 2][column] = 1;
			field[row - 2][column + 1] = 1;
			reverseL = 1;
		}
	}
	if(reverseL == 1) {
			row = getRow(yReverseL);
			column = (xReverseL / 50) - 2;
			if(yReverseL > 0 && field[row][column] == 0 && field[row][column + 1] == 0
					&& field[row][column + 2] == 0) {
				yReverseL = yReverseL - 50;
			}
			else {
				field[row - 1][column] = 1;
				field[row - 1][column + 1] = 1;
				field[row - 1][column + 2] = 1;
				field[row - 2][column + 2] = 1;
			}
		}
	for (int i = 0; i < 100000000; i++) {

	}

	glFlush();

	glutPostRedisplay();
}

void print(int x, int y, char *string)
{
	int len, i;
	glRasterPos2f(x, y);
	len = (int) strlen(string);

	for (i = 0; i < len; i++)
	{
		glutBitmapCharacter(GLUT_BITMAP_TIMES_ROMAN_24,string[i]);
	}
}

int getRow(int y) {
	int row = 0;
	row = ((800 - y ) / 50);
	return row;
}

void keyBoard(unsigned char key, int a, int b){
	glClear(GL_COLOR_BUFFER_BIT);
	if(key == 's') {
		start = 1;
		xRegularZ = 250;
		yRegularZ = 800;
		xReverseZ = 250;
		yReverseZ = 800;
		xSquare = 250;
		ySquare = 800;
		yRegularL = 800;
		xRegularL = 250;
		xReverseL = 250;
		yReverseL = 800;
		xBox = 250;
		yBox = 800;
		xTower = 250;
		yTower = 800;
		regularZ = 1;
		score = 0;
	}
	if(key == 'r') {
		//do the same as above and add the angle of rotation case

				xRegularZ = xRegularZ + 50;

	}

	glFlush();
	glutPostRedisplay();


}

int scoreCalc(int field[16][10]) {
	int i = 15;
	while(i < -1) {
		for (int j = 0; j < 10; j++) {
			if(field[i][j] == 1) {
				score++;
			}
			else {
				score = 0;
				i--;
			}
		}
		i--;
	}
	return score;
}

void gameEnd(int field[16][10]) {
	int i = 0;
	int sum = 0 ;
	char * stC[100];
	sprintf((char *)stC,"Game over with total score = %d",score);
	for (int j = 0; j < 10; j++) {
		if(field[i][j] == 1) {
			sum++;
		}
	}
	if(sum == 10) {
		glutIdleFunc(0);
		print(0,550,(char *)stC);
	}
}


//<<<<<<<<<<<<<<<<<<<<<<<< main >>>>>>>>>>>>>>>>>>>>>>
int main(int argc, char** argv)
{
	glutInit(&argc,argv);
	glutInitDisplayMode(GLUT_SINGLE|GLUT_RGB);
	glutInitWindowSize(500,800);
	glutInitWindowPosition(50,50);
	glutCreateWindow("Titres");
	glutDisplayFunc(myDisplay);
	glutIdleFunc(anim);//callig the anim func
	glutKeyboardFunc(keyBoard);//keyboard handler.
	glClearColor(0.0f,0.0f,0.2f,0.0f);
	gluOrtho2D(0.0,600.0,0.0,600.0);
	glutMainLoop();
	return 0;
// go into a perpetual loop
}
