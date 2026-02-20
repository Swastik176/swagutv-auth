# 🎥 SwaGUTv

> A private university-only random video chat platform built for real
> students, real conversations, and real vibes.

SwaGUTv is a secure, full-stack WebRTC-based platform that connects
verified university students for spontaneous 1-on-1 video
conversations.\
It combines authentication, matchmaking, signaling, and TURN relay
infrastructure to deliver reliable real-time communication across
different networks.

------------------------------------------------------------------------

## 🚀 Live Architecture

**Frontend** - React (Vite) - Tailwind CSS - Hosted on Vercel

**Backend Services** - Auth Service (Spring Boot + JWT) - Signaling
Service (Node.js + Socket.IO)

**Database** - PostgreSQL

**Infrastructure** - WebRTC (STUN + TURN) - Coturn (AWS EC2)

------------------------------------------------------------------------

# ✨ Core Features

## 🔐 Secure University-Only Access

-   Email domain restriction
-   OTP verification
-   JWT-based authentication
-   Role-based access control

## 🎥 Real-Time Video Chat

-   Peer-to-peer WebRTC connection
-   STUN for NAT traversal
-   TURN relay fallback for strict networks
-   Audio + Video streaming

## 🔄 Smart Matchmaking

-   Real-time queue system
-   Random student pairing
-   Instant "Next" switch
-   Clean disconnection handling

## ⏳ OTP System with Cooldown

-   Email OTP verification
-   Resend OTP with cooldown timer
-   Spam folder guidance message
-   Production-grade email provider support

## 🎨 Modern UI (GenZ Focused)

-   Startup-style dark theme
-   Fully responsive layout
-   Mobile stacked video layout
-   Clean typography and branding

------------------------------------------------------------------------

# 🧠 Unique Selling Proposition (USP)

### 🎓 University-Exclusive Community

-   Restricted to verified university emails
-   Safer and more meaningful conversations
-   Reduced anonymity abuse

### 🌍 Network-Resilient WebRTC Setup

-   Automatic fallback from STUN to TURN
-   Works across ISPs, NATs, and restricted campus WiFi

### 🏗 Real Infrastructure Deployment

-   Separate auth + signaling services
-   Dedicated TURN relay
-   Production database
-   Cloud-hosted architecture

------------------------------------------------------------------------

# 🛠 Tech Stack

### Frontend

-   React
-   Vite
-   Tailwind CSS
-   Axios
-   React Router

### Backend

-   Spring Boot
-   JWT Authentication
-   Node.js
-   Socket.IO

### Realtime

-   WebRTC
-   STUN
-   TURN (coturn)

### Database

-   PostgreSQL

### Cloud

-   AWS EC2
-   Railway
-   Vercel

------------------------------------------------------------------------

# 📈 Scaling Strategy

### Phase 1 -- Campus Launch

-   Single VPS (4GB RAM)
-   Run Auth + Signaling + Postgres + TURN

### Phase 2 -- Growth

-   Separate TURN server
-   Add Redis for signaling scaling
-   Add Load Balancer

------------------------------------------------------------------------

# 👨‍💻 Author

**Swastik Gupta**\
Final-year B.Tech CSE\
Backend-focused full-stack developer

------------------------------------------------------------------------

# ⭐ Why This Project Matters

SwaGUTv demonstrates: - Real-time systems design - Distributed
architecture - Cloud deployment - WebRTC infrastructure -
Production-level authentication
