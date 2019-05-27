#include "stdio.h"
#include "stdlib.h"
#include "time.h"

int main(void)
{
	int b = 0;
	time_t t;

	//Intializes random number generator
	srand((unsigned) time(&t));
	b = rand() % 3; // will return value from 0 to 2
	printf("rand value = %d\n", b);

	return 0;
}
