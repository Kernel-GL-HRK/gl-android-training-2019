#include <iostream>
using namespace std;

int main(int argc, char **argv) {
    bool exit = false;
    bool result = false;

    cout << "Lets play the game!!!" << endl;

    while (false == exit) {
        char number = '0';

        cout << "Guess a number from 0 to 9" << endl;
        cin >> number;

        if ('0' == number) {
            result = true;
        }

        cin.clear();
        exit = true;
    }

    if (result) {
        cout << "You've won!!!" << endl;
    } else {
        cout << "You've  lose!!!" << endl;
    }

    return 0;
}
