#ifndef FIELD_H_INCLUDED
#define FIELD_H_INCLUDED

#include <cstdint>
#include <vector>
#include <iostream>
#include "tile.h"

struct Pos {
    uint32_t x;
    uint32_t y;
};

class Field {
public:
    static Field generate(uint32_t width, uint32_t height, float mine_chance);

    bool reveal_tile(Pos pos);

    bool is_all_revealed() const;

    void print(std::ostream& str) const;

private:
    explicit Field(std::vector<Tile>&& tiles, uint32_t width, uint32_t height);

    Tile& at(Pos pos);
    const Tile& at(Pos pos) const;

    void reveal_next_tile(Pos pos);

    void print_tile(Pos pos, std::ostream& str) const;

    std::vector<Tile> tiles;
    uint32_t width;
    uint32_t height;
};

std::ostream& operator << (std::ostream& str, const Field& field);

#endif // FIELD_H_INCLUDED
