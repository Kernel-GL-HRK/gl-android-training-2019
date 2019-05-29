#include "field.h"
#include <algorithm>
#include <iterator>
#include <random>
#include <cassert>

Field::Field(std::vector<Tile>&& tiles, uint32_t width, uint32_t height)
    : tiles{std::move(tiles)}
    , width{width}
    , height{height} {}

Field Field::generate(uint32_t width, uint32_t height, float mine_chance) {
    assert(mine_chance > 0.0f && mine_chance < 1.0f);

    std::vector<Tile> tiles;
    std::generate_n(std::back_inserter(tiles), width * height, [&mine_chance] () {
        std::random_device device;
        std::mt19937 gen{device()};
        std::uniform_real_distribution<float> distr{0.0f, 1.0f};

        if (mine_chance > distr(gen)) {
            return Tile{false, true};
        } else {
            return Tile{false, false};
        }
    });

    return Field{std::move(tiles), width, height};
}

bool Field::reveal_tile(Pos pos) {
    assert(pos.x >= 0 && pos.x < width);
    assert(pos.y >= 0 && pos.y < height);

    Tile& tile = at(pos);
    tile.revealed = true;

    if (tile.mined) {
        return true;
    } else {
        reveal_next_tile({pos.x + 1, pos.y});
        reveal_next_tile({pos.x - 1, pos.y});
        reveal_next_tile({pos.x, pos.y + 1});
        reveal_next_tile({pos.x, pos.y - 1});

        return false;
    }
}

bool Field::is_all_revealed() const {
    return std::all_of(std::begin(tiles), std::end(tiles), [](Tile tile) {
        return tile.revealed || tile.mined;
    });
}

void Field::print(std::ostream& str) const {
    for (uint32_t y = 0; y < height; y++) {
        for (uint32_t x = 0; x < width; x++) {
            print_tile({x, y}, str);
        }
        str << "\n";
    }
}

Tile& Field::at(Pos pos) {
    return tiles[(pos.y * width) + pos.x];
}

const Tile& Field::at(Pos pos) const {
    return tiles[(pos.y * width) + pos.x];
}

void Field::reveal_next_tile(Pos pos) {
    if (pos.x >= width || pos.x < 0) {
        return;
    }

    if (pos.y >= height || pos.y < 0) {
        return;
    }

    Tile& tile = at(pos);
    if (!tile.mined && !tile.revealed) {
        tile.revealed = true;

        reveal_next_tile({pos.x + 1, pos.y});
        reveal_next_tile({pos.x - 1, pos.y});
        reveal_next_tile({pos.x, pos.y + 1});
        reveal_next_tile({pos.x, pos.y - 1});
    }
}

void Field::print_tile(Pos pos, std::ostream& str) const {
    const Tile& tile = at(pos);
    if (tile.revealed) {
        if (tile.mined) {
            str << 'b';
        } else {
            auto is_valid_pos = [this](Pos pos) {
                return pos.x >= 0 && pos.x < width
                    && pos.y >= 0 && pos.y < height;
            };

            uint32_t mines_count = 0;
            mines_count += (is_valid_pos({pos.x + 1, pos.y})     && at({pos.x + 1, pos.y}).mined) ? 1 : 0;
            mines_count += (is_valid_pos({pos.x - 1, pos.y})     && at({pos.x - 1, pos.y}).mined) ? 1 : 0;
            mines_count += (is_valid_pos({pos.x, pos.y + 1})     && at({pos.x, pos.y + 1}).mined) ? 1 : 0;
            mines_count += (is_valid_pos({pos.x, pos.y - 1})     && at({pos.x, pos.y - 1}).mined) ? 1 : 0;
            mines_count += (is_valid_pos({pos.x + 1, pos.y + 1}) && at({pos.x + 1, pos.y + 1}).mined) ? 1 : 0;
            mines_count += (is_valid_pos({pos.x - 1, pos.y - 1}) && at({pos.x - 1, pos.y - 1}).mined) ? 1 : 0;
            mines_count += (is_valid_pos({pos.x - 1, pos.y + 1}) && at({pos.x - 1, pos.y + 1}).mined) ? 1 : 0;
            mines_count += (is_valid_pos({pos.x + 1, pos.y - 1}) && at({pos.x + 1, pos.y - 1}).mined) ? 1 : 0;

            if (mines_count == 0) {
                str << '_';
            } else {
                str << mines_count;
            }
        }
    } else {
        str << '#';
    }
}

std::ostream& operator << (std::ostream& str, const Field& field) {
    field.print(str);
    return str;
}
