.PHONY: all clean

SRC := \
	color-quiz.c

OBJ := $(SRC:.c=.o)

all: color-quiz

color-quiz: $(OBJ)
	$(CC) $^ -o $@

%.o: %.c
	$(CC) -c $< -o $@

clean:
	rm -f $(OBJ) color-quiz
