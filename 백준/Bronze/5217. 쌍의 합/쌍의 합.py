t = int(input())
for _ in range(t):
    n = int(input())
    print(f'Pairs for {n}:',end='')
    for i in range(1, n//2+1):
        x = n-i
        if i != x:
            if i != 1:
                print(',',end='')
            print(f' {i} {x}' ,end='')
        else:
            break
    print()