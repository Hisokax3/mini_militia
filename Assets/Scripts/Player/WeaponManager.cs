using UnityEngine;
using UnityEngine.InputSystem;

public class WeaponManager : MonoBehaviour
{
    private Weapon[] weapons;
    private int currentWeaponIndex = -1;

    void Start()
    {
        weapons = GetComponentsInChildren<Weapon>();
        for (int i = 0; i < weapons.Length; i++)
        {
            weapons[i].gameObject.SetActive(false);
        }
        selectWeapon(currentWeaponIndex);
    }

    // Update is called once per frame
    void Update()
    {   
        if(currentWeaponIndex < 0 || currentWeaponIndex >= weapons.Length) return;
        if (Mouse.current.leftButton.isPressed)
        {
            weapons[currentWeaponIndex].fire();
        }
    }

    private void selectWeapon(int weapondIndex)
    {
        if (weapondIndex < 0 || weapondIndex >= weapons.Length) return;
        for (int i = 0; i < weapons.Length; i++)
        {
            weapons[i].gameObject.SetActive(i == weapondIndex);
        }
    }

    public void AddWeapon(int weaponIndex)
    {
        if (weaponIndex < 0 || weaponIndex >= weapons.Length)
        {
            Debug.LogError("Invalid weapon index: " + weaponIndex);
            return;
        }
        currentWeaponIndex = weaponIndex;
        selectWeapon(currentWeaponIndex);
        Debug.Log("Switched to weapon: " + weapons[currentWeaponIndex].weaponData.weaponName);
    }
}
