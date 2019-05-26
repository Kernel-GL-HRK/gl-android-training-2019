#include "stdio.h"
#include "stdlib.h"
#include "time.h"

char *choiceConvertor(char gameChoice)
{
  if (gameChoice == 'r')
    return "rock";
  if (gameChoice == 's')
    return "scissors";
  if (gameChoice == 'p')
    return "paper";
}

char readUser()
{
  printf("Please write one of: rock (r) - paper (p) - scissors (s)\n");
  char user;
  while (user != 'r' && user != 'p' && user != 's')
    scanf("%c", &user);
  return user;
}

int main(void)
{
  char userChoice = readUser();
  printf("User choice is: %s\n", choiceConvertor(userChoice));
  return 0;
}
