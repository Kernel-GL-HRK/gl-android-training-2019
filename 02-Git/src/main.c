#include <stdio.h>

int main()
{
    char buff[100];
    int n;

    printf("I made a number from 0 to 10. Try to guess.\n");
    for (;;)
    {
        printf("Please enter you number: ");
        const char *s = fgets(buff, sizeof(buff), stdin);
        if (!s || sscanf(s, "%d", &n) != 1)
        {
            printf("Not a number\n");
            continue;
        }

        printf("Entered number: %d\n", n);
    }
    return 0;
}
