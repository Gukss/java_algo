#include <iostream>
#include <string>
#include <algorithm>
#include <climits>
using namespace std;

int roomNum;
int v[10];
int len;
int iMin;
int result;

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	cin >> roomNum;
	len = to_string(roomNum).length();
	//많이 나온 숫자의 횟수를 담을 변수
	int max = INT_MIN;
	for (int i = 0; i < len; i++) {
		int target = roomNum % 10;
		v[target] += 1;
		roomNum /= 10;
		//6이랑 9의 횟수는 최대여도 저장하지 않는다.
		if (((target != 6) && (target != 9)) && max < v[target]) {
			max = v[target];
			//많이 나온 숫자의 idx를 담을 변수
			iMin = target;
		}
	}
	if ((v[6] + v[9]) % 2 == 0) {
		//6이랑 9 반으로 나눈게 더 크면 해당 숫자로 바꾼다.
		if ((v[6] + v[9]) / 2 > max) {
			max = (v[6] + v[9]) / 2;
		}
	}
	else {
		if ((v[6] + v[9]) / 2 + 1 > max) {
			max = (v[6] + v[9]) / 2 + 1;
		}
	}
	cout << max;
}