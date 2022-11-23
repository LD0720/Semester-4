
import numpy as np
import matplotlib.pyplot as plt
import sounddevice as sd
from scipy.fftpack import fft

t = np.linspace(0 , 3 , 12288)
left_frequency=np.array([349.23,369.99,392.00,1175,1397,1976])
right_frequency=np.array([415.30,440.00,493.88,1568,2093,988])
start = np.array([0,0.6,1.4,2,2.3,2.7])
end= np.array([0.5,1.2,1.9,2.2,2.6,3])

i=0
z=0
while(i<6):
    x1=np.sin(2*np.pi*left_frequency[i]*t)
    x2=np.sin(2*np.pi*right_frequency[i]*t)
    y1=np.where(np.logical_and(t>start[i],(t<end[i])),x1,0)
    y2=np.where(np.logical_and(t>start[i],(t<end[i])),x2,0)
    z=z+y1+y2
    i=i+1
    

fn1,fn2=np.random.randint(0,512,2)
n=3*1024
f=np.linspace(0,512,int(n/2))
x_f=fft(z)
x_f=2/n*np.abs(x_f[0:int(n/2)])
#plt.figure()
#plt.plot(f,x_f)
nt=np.sin(2*fn1*np.pi*t)+np.sin(2*fn2*np.pi*t)
xt=np.add(z,nt)
xt_f=fft(xt)
xt_f=2/n*np.abs(xt_f[0:int(n/2)])

j=0
maxA1=0

while (j<len(x_f)):
    if(x_f[j]>maxA1):
        maxA1=x_f[j]
    j=j+1
maxA1=round(maxA1)


fR = []
for i in range (len(xt_f)):
    if(round(xt_f[i])>maxA1):
        fR.append(i)
fR1 = round(f[fR[0]])
fR2 = round(f[fR[1]])

xfilterf2=xt-(np.sin(2*fR1*np.pi*t)+np.sin(2*fR2*np.pi*t))

xFiltered=fft(xfilterf2)
xFiltered=2/n*np.abs(xFiltered [0:int(n/2)])

plt.subplot(6,2,1)
plt.plot(t,z)
plt.subplot(6,2,2)
plt.plot(f,x_f)
plt.subplot(6,2,3)
plt.plot(t,xt)
plt.subplot(6,2,4)
plt.plot(f,xt_f)
plt.subplot(6,2,5)
plt.plot(t,xfilterf2)
plt.subplot(6,2,6)
plt.plot(f,xFiltered)
sd.play(xfilterf2, 3*1024)