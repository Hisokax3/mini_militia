using UnityEngine;
using UnityEngine.InputSystem;

public class Player : MonoBehaviour
{
    private Rigidbody2D playerRigidbody2D;
    private Camera gameCamera;
    private bool isPlayerFacingRight;
    private float deadZone;
    public float maxSpeed = 20f;
    public float inputForce = 30f;
    private float currentZRotationVelocity;
    private float rotationSmoothTime;

    private void Awake()
    {
        playerRigidbody2D = GetComponent<Rigidbody2D>();
        gameCamera = Camera.main;
        isPlayerFacingRight = true;
        rotationSmoothTime = 0.5f;
        deadZone = 2;
    }

    private void FixedUpdate()
    {
        HandlePlayerMovement();
        HandlePlayerDirection();
        ApplyRotationResetTorque();
    }

    private void HandlePlayerMovement()
    {

        if (Keyboard.current.wKey.isPressed)
        {
            playerRigidbody2D.AddForce(+inputForce * transform.up);
        }
        if (Keyboard.current.sKey.isPressed)
        {
            playerRigidbody2D.AddForce(-inputForce * transform.up);
        }
        if (Keyboard.current.aKey.isPressed)
        {
            if (isPlayerFacingRight)
            {
                playerRigidbody2D.AddForce(-inputForce * transform.right);
            }
            else
            {
                playerRigidbody2D.AddForce(+inputForce * transform.right);
            }

        }
        if (Keyboard.current.dKey.isPressed)
        {
            if (isPlayerFacingRight)
            {
                playerRigidbody2D.AddForce(+inputForce * transform.right);
            }
            else
            {
                playerRigidbody2D.AddForce(-inputForce * transform.right);
            }
        }

        playerRigidbody2D.linearVelocity = Vector2.ClampMagnitude(playerRigidbody2D.linearVelocity, maxSpeed);

    }

    private void HandlePlayerDirection()
    {
        Vector3 mousePosition = gameCamera.ScreenToWorldPoint(Mouse.current.position.ReadValue());
        mousePosition.z = 0f;

        if (mousePosition.x < transform.position.x - deadZone)
        {
            if (isPlayerFacingRight)
            {
                transform.Rotate(0f, 180f, 0f);
                isPlayerFacingRight = false;
            }
        }
        else if (mousePosition.x > transform.position.x + deadZone)
        {
            if (!isPlayerFacingRight)
            {
                transform.Rotate(0f, 180f, 0f);
                isPlayerFacingRight = true;
            }
        }
    }

    private void ApplyRotationResetTorque()
    {   
        float currentZAngle = transform.eulerAngles.z;
        
        // Use Mathf.SmoothDampAngle to smoothly change the angle towards 0 (upright)
        float newZAngle = Mathf.SmoothDampAngle(currentZAngle, 0f, ref currentZRotationVelocity, rotationSmoothTime);

        float yrotation = isPlayerFacingRight ? 0f : 180f;
        
        transform.rotation = Quaternion.Euler(0, yrotation, newZAngle);
    }
}
