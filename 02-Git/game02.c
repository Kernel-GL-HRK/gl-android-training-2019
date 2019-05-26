#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define RET_OK 	0
#define RET_ERR 1

#define RANGE_MIN 1
#define RANGE_MAX 10

int getProgNum(int , int );

int main()
{
	int progNum;
	int userNum;
	int ret;

	printf("Hello! It is \"Game02\"!\n");

	//Generate the random value in the range:
	progNum = getProgNum(RANGE_MIN, RANGE_MAX);

	printf("I am thinking of a number between %d and %d. Can you guess what is this number?\n", RANGE_MIN, RANGE_MAX);
	printf(" [%d..%d]: ", RANGE_MIN, RANGE_MAX);
	ret = scanf("%d", &userNum);
	if (ret != 1 || userNum < RANGE_MIN || userNum > RANGE_MAX)
	{
		printf("Incorrect input is detected! Exit.\n");
		return RET_ERR;
	}

	//Here check the input value and print the game result:
	printf("\nI thought of the number %d. You %s !\n\n", progNum, progNum == userNum ? "WON" : "LOSE");	

	return RET_OK;
}

//Returns a random value in the range from 'valFrom' to 'valTo'.
int getProgNum(int valFrom, int valTo)
{
	int ret;
	
	if (valFrom == valTo)
	  return valFrom;

	//Reset random sequence:
	srand(time(NULL));
	//Generate random number:
	ret = rand() % (abs(valTo-valFrom)) + (valFrom<valTo ? valFrom : valTo);

	return ret;
}




