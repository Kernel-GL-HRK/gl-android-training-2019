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
	NULL,
};

static unsigned get_colors_size(const char *colors[])
{
	unsigned rv = 0;

	for (const char **p = colors; *p; p++, rv++);

	return rv;
}

int main(int argc, char *argv[])
{
	unsigned color_idx = rand() % get_colors_size(colors);
	char *answer = "";
	const char *guess = colors[color_idx];

	if (argc > 1) {
		answer = argv[1];
	}

	printf("%s\r\n", !strcmp(answer, guess) ? "Bingo" : "Missed");
	return 0;
}
