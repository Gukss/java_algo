#include <iostream>
#include <string>
#include <list>

using namespace std;

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);

	string s;
	list<char> list;
	int num;

	getline(cin, s);
	cin >> num;

	//list 입력받은 값으로 초기화
	for (auto e : s) {
		list.push_back(e);
	}

	//iter가 마지막을 가리키고 있게
	auto cursor = list.end();
	char input;
	char ch;
	for (int i = 0; i < num; i++) {
		cin >> input;
		if (input == 'P') {
			//문자를 하나 더 입력받고, iter가 가리키고 있는 곳에 추가한다.
			cin >> ch;
			list.insert(cursor, ch);
		}
		else if (input == 'L') {
			//iter가 처음이 아니면 왼쪽으로 이동한다.
			if (cursor != list.begin()) {
				cursor--;
			}
		}
		else if (input == 'D') {
			//iter가 마지막이 아니면 오른쪽으로 이동한다.
			if (cursor != list.end()) {
				cursor++;
			}
		}
		else if (input == 'B') {
			//iter가 처음이 아니면 왼쪽으로 이동해서 하나 삭제하고 
			// 그 오른쪽을 가리키고 있는 iter로 cursor를 초기화해준다.
			if (cursor != list.begin()) {
				cursor--;
				cursor = list.erase(cursor);
			}
		}
	}
	for (auto e : list) {
		cout << e;
	}
}