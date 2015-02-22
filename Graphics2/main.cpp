//#include <stdafx.h>
#include <GL/glut.h>
#include <math.h>
#include <string.h>
#include <stdio.h>


int size = 5;
int top[5][80],bottom[5][80],depth[5][5],left[5][80],right [5][80];
double eyez = -10;
int eyex = 6;
double xcone = 6;
double ycone = 5;
double zcone = 0;
double xsphere = 6;
double ysphere = 6;
double zsphere = 10;
int zrotate = 0;
int direction;
int activate = 0;
double centerz = 5;
int rotatez= 0;
int score;
void print(int x, int y,int z, char *string);
void wall(double thickness){
glPushMatrix();
glColor3d(1,0,0);
glTranslated(0.5,0.5*thickness,0.5);
glScaled(1.0,thickness,1.0);
glutSolidCube(1);
glPopMatrix();
}
void cone(int x, int y, int z) {
glPushMatrix();
glTranslated(xcone,ycone,zcone);
glutSolidCone(0.2, 0.5, 10, 8);
glPopMatrix();
}
void sphere(int x, int y, int z) {
	glPushMatrix();
	glColor3d(1,1,0);
	glTranslated(xsphere,ysphere,zsphere);
	glutSolidSphere(0.15, 10, 10);
	glPopMatrix();
}
void brick(int red, int green, int blue, double x, double y, double z) {
	glPushMatrix();
	glColor3d(red,green,blue);
	glTranslated(5 + x,5 + y,100 + z);
	glScaled(1.0,1.0,1.0);
	glutSolidCube(0.5);
	glPopMatrix();
}

void SetupLights()
{
GLfloat mat_ambient[] = { 0.7f, 0.7f, 0.7, 1.0f };
GLfloat mat_diffuse[] = { 0.6f, 0.6f, 0.6, 1.0f };
GLfloat mat_specular[] = { 1.0f, 1.0f, 1.0, 1.0f };
GLfloat mat_shininess[] = { 50 };
glMaterialfv(GL_FRONT_AND_BACK, GL_AMBIENT, mat_ambient);
glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse);
glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular);
glMaterialfv(GL_FRONT, GL_SHININESS, mat_shininess);
//set the light source properties
GLfloat lightIntensity[] = { 0.7f, 0.7f, 1, 1.0f };
GLfloat light_position[] = { -7.0f, 6.0f, 3.0f, 0.0f };
glLightfv(GL_LIGHT0, GL_POSITION, lightIntensity);
glLightfv(GL_LIGHT0, GL_DIFFUSE, lightIntensity);
}
void displayWire(void){
// Setup light
//SetupLights();
//set the camera
glMatrixMode(GL_PROJECTION);
glLoadIdentity();
glOrtho(-2, 2, -2, 2, -1, 1);
gluPerspective(45, 1, 0.1, 100);
glMatrixMode(GL_MODELVIEW);
glLoadIdentity();
glRotated(rotatez,0,0,1);
gluLookAt(eyex,6,eyez,5,centerz,100,0,1,0);

//start drawing
glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
//----- > Your Code !!
// here we make a for loop starting from o to 25 and w increment both i and j to draw the wall
int red = 0;
int green = 0;
int blue = 0;
double x = 0;
double y = 0;
double z = 0;
char * stC[500];
sprintf((char *)stC,"score = %d",score);
print(-10,10,20,(char *)stC);
char * stC2[500];

sprintf((char *)stC2,"press p to start the game, use l,r,u,d buttons for left,right,up,down to"
		" provide the initial direction of the ball ");
print(6,10,0,(char *)stC2);
for ( int i = 0; i < size; i++) {
	for (int j = 0; j < size; j++) {
		if(depth[i][j] == 0) {
			red = 1;
			green = 0;
			blue = 0;
		}
		if(depth[i][j] == 1) {
			green = 1;
			red = 0;
			blue = 0;
		}
		if(depth[i][j] == 2) {
			blue = 1;
			red = 0;
			green = 0;
		}
		if(depth[i][j] == 3) {
			red = 0;
			green = 0;
			blue = 0;
		}
		glPushMatrix();
		brick(red,green,blue, x, y, -50);
		x = x + 0.5;
		glPopMatrix();
	}
	glPushMatrix();
	x = 0;
	brick(red,green,blue,x,y, -40);
	y = y + 0.5;
	glPopMatrix();
}
x = 0;
y = 0;
red = 0;
green = 0;
blue = 0;
// the loop for the right wall
for ( int i = 0; i < size; i++) {
	for (int j = 0; j < 80; j++) {
		if(right[i][j] == 0) {
			red = 1;
			green = 0;
			blue = 0;
		}
		if(right[i][j] == 1) {
			green = 1;
			red = 0;
			blue = 0;
		}
		if(right[i][j] == 2) {
			blue = 1;
			red = 0;
			green = 0;
		}
		if(right[i][j] == 3) {
			red = 0;
			green = 0;
			blue = 0;
		}
		glPushMatrix();
		brick(red,green,blue, -0.5, y, z - 90);
		z = z + 0.5;
		glPopMatrix();
	}
	glPushMatrix();
	z = 0;
	brick(red,green,blue,-0.5,y, -90);
	y = y + 0.5;
	glPopMatrix();
}
x = 0;
y = 0;
red = 0;
green = 0;
blue = 0;
z = 0;
//the construction of the left wall
for ( int i = 0; i < size; i++) {
	for (int j = 0; j < 80; j++) {
		if(left[i][j] == 0) {
			red = 1;
			green = 0;
			blue = 0;
		}
		if(left[i][j] == 1) {
			green = 1;
			red = 0;
			blue = 0;
		}
		if(left[i][j] == 2) {
			blue = 1;
			red = 0;
			green = 0;
		}
		if(left[i][j] == 3) {
			red = 0;
			green = 0;
			blue = 0;
		}
		glPushMatrix();
		brick(red,green,blue, 2.5, y, z - 90);
		z = z + 0.5;
		glPopMatrix();
	}
	glPushMatrix();
	z = 0;
	brick(red,green,blue,2.5,y, -90);
	y = y + 0.5;
	glPopMatrix();
}
x = 0;
y = 0;
red = 0;
green = 0;
blue = 0;
z = 0;
//construction of the bottom wall
for ( int i = 0; i < size; i++) {
	for (int j = 0; j < 80; j++) {
		if(bottom[i][j] == 0) {
			red = 1;
			green = 0;
			blue = 0;
		}
		if(bottom[i][j] == 1) {
			green = 1;
			red = 0;
			blue = 0;
		}
		if(bottom[i][j] == 2) {
			blue = 1;
			red = 0;
			green = 0;
		}
		if(bottom[i][j] == 3) {
			red = 0;
			green = 0;
			blue = 0;
		}
		glPushMatrix();
		brick(red,green,blue, x, y, z - 90);
		z = z + 0.5;
		glPopMatrix();
	}
	glPushMatrix();
	z = 0;
	brick(red,green,blue,x,y, -90);
	x = x + 0.5;
	glPopMatrix();
}
x = 0;
y = 0;
red = 0;
green = 0;
blue = 0;
z = 0;
//construction of the upper wall
for ( int i = 0; i < size; i++) {
	for (int j = 0; j < 80; j++) {
		if(top[i][j] == 0) {
			red = 1;
			green = 0;
			blue = 0;
		}
		if(top[i][j] == 1) {
			green = 1;
			red = 0;
			blue = 0;
		}
		if(top[i][j] == 2) {
			blue = 1;
			red = 0;
			green = 0;
		}
		if(top[i][j] == 3) {
			red = 0;
			green = 0;
			blue = 0;
		}
		glPushMatrix();
		brick(red,green,blue, x, 2, z - 90);
		z = z + 0.5;
		glPopMatrix();
	}
	glPushMatrix();
	z = 0;
	brick(red,green,blue,x,2, -90);
	x = x + 0.5;
	glPopMatrix();
}
//
glPushMatrix();
cone(xcone,ycone,zcone);
sphere(xsphere,ysphere,zsphere);
glPopMatrix();
glFlush();
}

void keyBoard(unsigned char key, int a, int b){

	if(key == 's') {
		eyez++;
	}
	if(key == 'a') {
		eyex++;
	}
	if(key == 'g') {
		if(zsphere != 50) {
		zsphere+=0.5;
		zcone+=0.5;
		}
	}
	if(key == 'l') {
		if(xsphere != 7.5) {
		xsphere+=0.5;
		xcone+=0.5;
		}
	}
	if(key == 'r') {
		if(xsphere != 4.5) {
			xsphere-=0.5;
			xcone-=0.5;
		}
	}
	if(key == 'u') {
		if(ysphere != 7) {
		ysphere+=0.5;
		ycone+=0.5;
		}
	}
	if(key == 'd') {
		if(ysphere != 5) {
			ysphere-=0.5;
			ycone-=0.5;
		}
	}
	if(key == 'p') {
		activate = 1;
	}
	glutPostRedisplay();
}

void calculateAllExceptFront(int array [5][80] ,int row, int column) {
	int color = array[row%5][column%80];
	if(color == 0) {
		score+=10;
	}
	if(color == 1) {
		score+=20;
	}
	if(color == 2) {
		score-=10;
	}
	if(color == 3) {
		score-=20;
	}
}
void calculateFrontWall(int array [5][5] ,int row, int column) {
	int color = array[row%5][column%5];
	if(color == 0) {
		score+=10;
	}
	if(color == 1) {
		score+=20;
	}
	if(color == 2) {
		score-=10;
	}
	if(color == 3) {
		score-=20;
	}
}
void anim() {
	glClear(GL_COLOR_BUFFER_BIT);
	if(direction == 0){
	if(ysphere != 5 && ysphere == 7) {
		ysphere-=0.5;
		ycone-=0.5;
		centerz=+1;
		calculateAllExceptFront(bottom,ysphere,zsphere);
	}
	direction = rand() % 5;

	}
	if(direction == 1) {
	if(ysphere != 7 ) {
		ysphere+=0.5;
		ycone+=0.5;
		centerz=+1;
		calculateAllExceptFront(top,ysphere,zsphere);
	}
	direction = rand() % 5;
	}
	if (direction == 2) {
	if(xsphere != 4.5 && xsphere == 7.5) {
		xsphere-=0.5;
		xcone-=0.5;
		centerz=+1;
		calculateAllExceptFront(right,xsphere,zsphere);
	}
	direction = rand() % 5;
	}
	if(direction == 3){
	if(xsphere != 7.5) {
		xsphere+=0.5;
		xcone+=0.5;
		centerz=+1;
		calculateAllExceptFront(left,xsphere,zsphere);
	}
	direction = rand() % 5;
	}
	if(direction == 4){
	if(zsphere != 50) {
		zsphere+=0.5;
		zcone+=0.5;
		centerz=+1;
	}
	else {
		calculateFrontWall(depth,xsphere,ysphere);
		char * stC[100];
		sprintf((char *)stC,"Game over with total score = %d",score);
		glutIdleFunc(0);
	}
	direction = rand() % 5;
	}
	if(eyez < zcone) {
		eyez = eyez + 0.25;
	}
	else {
		eyez = zcone;
	}
	if(rotatez != 360) {
		rotatez = rotatez + 5;
	}
	else {
		rotatez = 0;
	}
	for (int i = 0; i < 10000000; i++) {

		}
	glutKeyboardFunc(0);
	glFlush();
	glutPostRedisplay();
}

void playAnim() {
	if(activate == 1) {
		anim();
	}
}
void print(int x, int y,int z, char *string)
{
	int len, i;
	glRasterPos3d(x,y,z);
	len = (int) strlen(string);

	for (i = 0; i < len; i++)
	{
		glutBitmapCharacter(GLUT_BITMAP_TIMES_ROMAN_24,string[i]);
	}
}


int main(int argc, char** argv) {
	for(int i = 0; i < size; i++) {
		for(int j = 0; j < size; j++) {
			depth[i][j] = rand() % 4;
		}
	}
	for(int i = 0; i < size; i++) {
		for(int j = 0; j < 80; j++) {
			left[i][j] = rand() % 4;
			right[i][j] = rand() % 4;
		}
	}
	for(int i = 0; i < size; i++) {
			for(int j = 0; j < 80; j++) {
				top[i][j] = rand() % 4;
				bottom[i][j] = rand() % 4;
			}
		}
direction = rand()%5;
activate = 0;
score = 0;
glutInit(&argc, argv);
glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB|GLUT_DEPTH);
glutInitWindowSize(640,480);
glutInitWindowPosition(200, 150);
glutCreateWindow("Weird Game ");
glutDisplayFunc(displayWire);
/*glEnable(GL_LIGHTING);
glEnable(GL_LIGHT0);
glShadeModel(GL_SMOOTH);
glEnable(GL_DEPTH_TEST);
glEnable(GL_NORMALIZE);*/
glutKeyboardFunc(keyBoard);
glutIdleFunc(playAnim);
glClearColor(1.0,1.0,1.0,0.0);
glutMainLoop();
return 0;
}

