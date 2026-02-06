# 🔐 Pawlow's PermKit

<div align="center">

> Manage your server permissions with an intuitive UI. Activate logging for auditing and so much more!

### Fan of my work?
[![ko-fi](https://ko-fi.com/img/githubbutton_sm.svg)](https://ko-fi.com/E1E5H93UN)

![GitHub stars](https://img.shields.io/github/stars/ThePawlow/Pawlows-PermKit?style=social)
![GitHub forks](https://img.shields.io/github/forks/ThePawlow/Pawlows-PermKit?style=social)

</div>

---

## ✨ Features

- 🎨 **Intuitive UI** - Easy-to-use interface for managing server permissions
- 📝 **Audit Logging** - Track all permission changes with comprehensive logging
- ⚡ **Performance Focused** - Lightweight and optimized for Hytale servers
- 🔧 **Highly Configurable** - Customize permissions to fit your server's needs
- 🌍 **Multi-language Ready** - Powered by [Pawlows-HyLang](https://github.com/ThePawlow/Pawlows-HyLang) for localization

---

## 📦 Installation

1. Download the latest release from the [Releases](https://github.com/ThePawlow/Pawlows-PermKit/releases) page
2. Place the `.jar` file in your server's `mods` folder
3. Restart your server
4. Configure permissions through the in-game UI or config files

---

## 🔨 Building from Source

### Prerequisites
- Java Development Kit (JDK)
- Maven

### Build Commands

**With default output directory:**
```bash
mvn package -f pom.xml
```

**With custom output directory:**
```bash
mvn package "-Dmod.output.dir=/run/media/pawlow/Data (Games_Dev)/Hytale/servers/latest/mods" -f pom.xml
```

---

## 🚀 Usage

### Setting Up Permissions

1. Launch your Hytale server with PermKit installed
2. Access the permissions UI using the in-game command or admin panel
3. Create permission groups and assign roles
4. Enable audit logging in the configuration file

### Configuration

Edit the `permkit.config` file to customize:
- Permission group hierarchies
- Logging verbosity
- UI preferences
- Default permissions for new players

---

## 📚 Documentation

For detailed documentation on:
- Permission node structure
- API integration
- Advanced configuration
- Troubleshooting

Visit the [Wiki](https://github.com/ThePawlow/Pawlows-PermKit/wiki) *(coming soon)*

---

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

<div align="center">

**Built with ❤️ for the Hytale community**

[![GitHub](https://img.shields.io/badge/GitHub-ThePawlow-181717?style=flat&logo=github)](https://github.com/ThePawlow)
[![Ko-fi](https://img.shields.io/badge/Support%20me-FF5E5B?style=flat&logo=ko-fi&logoColor=white)](https://ko-fi.com/E1E5H93UN)

</div>