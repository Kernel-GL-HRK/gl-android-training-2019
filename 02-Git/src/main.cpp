#include <cstdlib>
#include "field.h"

Pos get_pos_input() {
    Pos pos;
    std::cin >> pos.x >> pos.y;
    return pos;
}

void run() {
    Field field = Field::generate(10, 10, 0.3f);

    bool has_exploded = false;
    bool has_won = false;
    do {
        std::cout << field << "\n";

        std::cout << "Enter tile to reveal: ";
        Pos pos = get_pos_input();
        has_exploded = field.reveal_tile(pos);
        has_won = field.is_all_revealed();
        if (has_exploded) {
            std::cout << field << "\n\n";
            std::cout << "You've lost!\n\n";
        } else if (has_won) {
            std::cout << field << "\n\n";
            std::cout << "You've won!\n\n";
        }
    } while (!has_exploded && !has_won);
}

int main() {
    run();

    return EXIT_SUCCESS;
}
