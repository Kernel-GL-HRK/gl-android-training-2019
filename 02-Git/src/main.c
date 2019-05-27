#include <stdlib.h>
#include <stdio.h>

int main()
{
    char buff[100];
    int n;
    int a = rand() % 10;
    int c = 3;

    printf("I made a number from 0 to 10. Try to guess. You have %d attempt(s).\n", c);
    for (int i = 0; i < c; ++i)
    {
        printf("Please enter you number: ");
        const char *s = fgets(buff, sizeof(buff), stdin);
        if (!s || sscanf(s, "%d", &n) != 1)
        {
            printf("Not a number\n");
            continue;
        }

        printf("Entered number: %d\n", n);

        if (n == a)
        {
            printf("Guessed. Goodby.\n");
            return 0;
        }
        if (n < a)
        {
            printf("The guess number is greated\n");
            continue;
        }
        if (n > a)
        {
            printf("The guess number is less\n");
            continue;
        }
    }
    printf("Did not guessed. Goodby.\n");
    return 0;
}
