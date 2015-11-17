#include <iostream>
#include <string>

using namespace std;

const int ARGS_REQUIRED = 2;

bool hasSubstring(const string x, const string y) {
	if (x.size() == 0) { // null string is always in all other strings
		return true;
	}
	for (unsigned int i = 0; i < y.size(); i++) {
		bool matching = true;
		for (unsigned int j = 0; j < x.size(); j++) {
			if ((i+j) >= y.size() || y.at(i + j) != x.at(j)) {
				matching = false;
				break;
			}
		}
		if (matching) {
			return true;
		}
	}
	return false;
}

int main(int argc, char* argv[]) {
	if (argc != ARGS_REQUIRED + 1) {
		cerr << "Input consists of two strings: x, y." << endl;
		return -1;
	}
	string x = argv[1];
	string y = argv[2];
	cout << (hasSubstring(x, y) ? "YES" : "NO");
	return 0;
}