# Pythonic Code
- 파이썬 스타일의 코딩기법
- 파이썬 특유의 문법을 활용하여 효율적으로 코드를 표현함
- 고급 코드를 작성 할 수록 더 많이 필요해짐

## split 
- String Type의 값을 나눠서 List 형태로 변환
```python
items = 'zero one two three'.split();
print(items)
example = "python, jquery,javascript".split(",");
print(example)
url = 'cs50.gachon.edu'
subdomain, domain, tld = url.split('.') # unpacking
print(subdomain, domain, tld)
```
## join
- String List를 합쳐 하나의 String 으로 반환할때 사용
```python
colors = ['red','blue','green','yellow']
result = ''.join(colors)
print(result)
result = ', '.join(colors)
print(result)
result = '-'.join(colors)
print(result)
```

## List comprehensions
- 기존 List 사용하여 간단히 다른 List를 만드는 기법
- 포괄적인 List, 포함되는 리스트라는 의미로 사용됨
- 파이썬에서 가장 많이 사용되는 기법중 하나
- 일반적으로 For + append보다 속도가 빠름

```python
# 일반적인 스타일
result = []
for i in range(10):
    result.append(i)
print(result)
# List Comprehension
result2 = [i for i in range(10)]
print(result2)
result3 = [i for i in range(10) if i % 2 == 0]
print(result3)
```
```python
word_1 = "Hello"
word_2 = "World"
result = [i+j for i in word_1 for j in word_2]
print(result)
words = ['The','quick','brown']
stuff = [[w.upper(), w.lower(), len(w)] for w in words]
print(stuff)
```

## Enumerate
- List의 element를 추철할때 번호를 붙여서 추출
```python
for i, v in enumerate(['tic','tac','toe']):
    print(i,v)
mylist = ["a","b","c","d"]
print(list(enumerate(mylist))) # list의 있는 index와 값을 unpacking 하여 list로 저장    
```

## Zip
- 두 개의 list의 값을 병렬적으로 추출함
```python
alist = ['a1','a2','a3']
blist = ['b1','b2','b3']
for a, b in zip(alist, blist): # 병렬적으로 값을 추출
    print(a,b)
```