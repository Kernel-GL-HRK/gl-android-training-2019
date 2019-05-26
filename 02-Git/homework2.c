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

char getComputerChoice()
{
  static char arr[] = {'r', 'p', 's'};

  srand(time(NULL));
  int rnd = rand() % 3;

  return arr[rnd];
}

void printGameResult(char userChoice, char computerChoice)
{

  printf("You choose %s, I choose %s\n",
         choiceConvertor(userChoice), choiceConvertor(computerChoice));

  if (userChoice == computerChoice)
    printf("Draw!!!!\n");
  else if ((userChoice == 'r') && (computerChoice == 's') ||
           (userChoice == 's') && (computerChoice == 'p') ||
           (userChoice == 'p') && (computerChoice == 'r'))
    printf("You Win: %s beats %s!!! Great job.\n",
           choiceConvertor(userChoice), choiceConvertor(computerChoice));
  else
    printf("You Lose: %s beats %s!!! Sorry, try again.\n",
           choiceConvertor(userChoice), choiceConvertor(computerChoice));
}

int main(void)
{
  char userChoice = readUser();
  char computerChoice = getComputerChoice();

  printGameResult(userChoice, computerChoice);

  return 0;
}
