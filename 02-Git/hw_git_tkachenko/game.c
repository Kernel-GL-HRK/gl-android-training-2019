#include <signal.h>
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

#define MAX_ID     3
#define CHOISE_REQ "Make your choice:\n\t0 - rock\n\t1 - paper\n\t2 - scissors\nGive me a number: "
#define EXIT_REQ   "Exit? (1 - yes, 0 - no): "

static int running;

static void my_handler(int s)
{
    running = 0;
    return;
}

static unsigned int random_choice()
{
    return rand() % (MAX_ID);
}

static unsigned int get_user_input(char *request, unsigned int max_val)
{
    unsigned int ui;
    int c;
    printf("%s", request);
    while(running)
    {
        if (scanf("%1d", &ui) != 1 || ui > max_val)
        {
            printf("Invalid input! Try again: ");
            continue;
        }
        break;
    }
    while ((c = fgetc(stdin)) != '\n' && c != EOF); /* Flush stdin */
    return ui;
}

static char* get_result_str(unsigned int ai_choice, unsigned int user_choice)
{
    if (ai_choice == user_choice)
        return "Draw";
    switch (user_choice)
    {
        case 0:
            return (ai_choice == 1) ? "Lose" : "Win";
        case 1:
            return (ai_choice == 2) ? "Lose" : "Win";
        case 2:
            return (ai_choice == 0) ? "Lose" : "Win";
        default:
            break;
    }
    return "Unknown result";
}

static char* get_name_by_id(unsigned int id)
{
    switch (id)
    {
        case 0:
            return "rock";
        case 1:
            return "paper";
        case 2:
            return "scissors";
        default:
            break;
    }
    return "unknown";
}

static int run_game(void)
{
    unsigned int user_choice, ai_choice;
    running = 1;
    printf("Lets play!\n\n");
    while (running)
    {
        ai_choice = random_choice();
        user_choice = get_user_input(CHOISE_REQ, (MAX_ID - 1));
        printf("%s with %s! (AI choice was %s)\n", get_result_str(ai_choice, user_choice), get_name_by_id(user_choice), get_name_by_id(ai_choice));
        if (get_user_input(EXIT_REQ, 1))
            break;
        printf("\n\n");
    }
    printf("\nBye!\n");
    return 0;
}

int main(void)
{
    int error = 0;
    struct sigaction sigIntHandler;
    sigIntHandler.sa_handler = my_handler;
    sigemptyset(&sigIntHandler.sa_mask);
    sigIntHandler.sa_flags = 0;
    sigaction(SIGINT, &sigIntHandler, NULL);
    error = run_game();
    return error;
}
