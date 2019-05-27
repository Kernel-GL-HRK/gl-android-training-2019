#include <stdlib.h>
#include <stdio.h>
#include <string.h>

static const char *colors[] = {
	"Red",
	"Orange",
	"Yellow",
	"Green",
	"Light-Blue",
	"Blue",
	"Purple",
	"",
};

#define ARRAY_SIZE(x) (sizeof(x) / sizeof((x)[0]))

int main(int argc, char *argv[])
{
	unsigned color_idx = rand() % ARRAY_SIZE(colors);
	char *answer = "";
	const char *guess = colors[color_idx];

	if (argc > 1) {
		answer = argv[1];
	}

	printf("%s ('%s' vs '%s')\r\n", !strcmp(answer, guess) ? "Bingo" : "Missed", guess, answer);
	return 0;
}
